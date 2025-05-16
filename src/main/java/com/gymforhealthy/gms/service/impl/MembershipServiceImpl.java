package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.MembershipRequestDto;
import com.gymforhealthy.gms.dto.responseDto.MembershipResponseDto;
import com.gymforhealthy.gms.entity.Membership;
import com.gymforhealthy.gms.entity.MembershipPackage;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.repository.MembershipRepository;
import com.gymforhealthy.gms.repository.MembershipPackageRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.MembershipService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;
    private final MembershipPackageRepository membershipPackageRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public MembershipResponseDto saveMembership(MembershipRequestDto membershipRequestDto) {
        // DTO'yu entity'ye dönüştür
        Membership membership = modelMapper.map(membershipRequestDto, Membership.class);
        membership.setId(null); // Yeni kayıt için ID'yi sıfırla

        // Kullanıcıyı bul, yoksa hata fırlat
        User user = userRepository.findById(membershipRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + membershipRequestDto.getUserId()));

        // Paketi bul, yoksa hata fırlat
        MembershipPackage membershipPackage = membershipPackageRepository.findById(membershipRequestDto.getPackageId())
                .orElseThrow(() -> new RuntimeException("Membership package not found with id: " + membershipRequestDto.getPackageId()));

        // Kullanıcı yalnızca MEMBER rolüne sahipse üyelik alabilir
        if (!user.getRole().getName().equalsIgnoreCase("MEMBER")) {
            throw new IllegalArgumentException("Only users with MEMBER role can have a membership.");
        }

        membership.setUser(user);
        membership.setMembershipPackage(membershipPackage);

        // remainingHours'u paketten al
        if (membershipPackage.getIsUnlimited()) {
            membership.setRemainingHours(null); // Sınırsız paket için null
        } else {
            membership.setRemainingHours(membershipPackage.getPackageTotalHour());
        }

        // Status'u tarih bazlı kontrol et
        updateMembershipStatus(membership);

        // Entity'yi kaydet ve DTO'ya dönüştür
        membership = membershipRepository.save(membership);
        return convertToResponseDto(membership);
    }

    @Override
    public MembershipResponseDto updateMembership(Long id, MembershipRequestDto membershipRequestDto) {
        // Mevcut kaydı bul, yoksa hata fırlat
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found with id: " + id));

        // DTO'dan gelen verileri mevcut entity'ye kopyala
        modelMapper.map(membershipRequestDto, membership);

        // Kullanıcıyı bul, yoksa hata fırlat
        User user = userRepository.findById(membershipRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + membershipRequestDto.getUserId()));

        // Paketi bul, yoksa hata fırlat
        MembershipPackage membershipPackage = membershipPackageRepository.findById(membershipRequestDto.getPackageId())
                .orElseThrow(() -> new RuntimeException("Membership package not found with id: " + membershipRequestDto.getPackageId()));

        // Kullanıcı yalnızca MEMBER rolüne sahipse üyelik alabilir
        if (!user.getRole().getName().equalsIgnoreCase("MEMBER")) {
            throw new IllegalArgumentException("Only users with MEMBER role can have a membership.");
        }

        membership.setUser(user);
        membership.setMembershipPackage(membershipPackage);

        // remainingHours'u paketten güncelle (eğer değişmediyse)
        if (membershipPackage.getIsUnlimited()) {
            membership.setRemainingHours(null);
        } else if (membership.getRemainingHours() == null || membership.getRemainingHours() > membershipPackage.getPackageTotalHour()) {
            membership.setRemainingHours(membershipPackage.getPackageTotalHour());
        }

        // Status'u tarih bazlı kontrol et
        updateMembershipStatus(membership);

        // Güncellenmiş entity'yi kaydet ve DTO'ya dönüştür
        membership = membershipRepository.save(membership);
        return convertToResponseDto(membership);
    }

    @Override
    public List<MembershipResponseDto> findAllMemberships() {
        // Tüm üyelikleri bul ve DTO listesine dönüştür
        return membershipRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    @Override
    public MembershipResponseDto findMembershipById(Long id) {
        // ID ile üyeliği bul, yoksa hata fırlat
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found with id: " + id));

        // Status'u güncelle
        updateMembershipStatus(membership);
        membershipRepository.save(membership);

        // Entity'yi DTO'ya dönüştür
        return convertToResponseDto(membership);
    }

    @Override
    public void deleteMembership(Long id) {
        // ID ile üyeliği bul, yoksa hata fırlat
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found with id: " + id));

        // Üyeliği sil
        membershipRepository.delete(membership);
    }

    private void updateMembershipStatus(Membership membership) {
        LocalDate today = LocalDate.now();
        if (membership.getEndDate() != null && membership.getEndDate().isBefore(today)) {
            membership.setMembershipStatus(Membership.MembershipStatus.EXPIRED);
        } else if (membership.getStartDate() != null && membership.getStartDate().isAfter(today)) {
            membership.setMembershipStatus(Membership.MembershipStatus.ACTIVE);
        } else {
            membership.setMembershipStatus(Membership.MembershipStatus.ACTIVE);
        }
    }

    private MembershipResponseDto convertToResponseDto(Membership membership) {
        // Entity'yi DTO'ya dönüştür
        MembershipResponseDto membershipResponseDto = modelMapper.map(membership, MembershipResponseDto.class);
        membershipResponseDto.setUserId(membership.getUser().getId());
        membershipResponseDto.setPackageName(membership.getMembershipPackage().getName());
        return membershipResponseDto;
    }
}