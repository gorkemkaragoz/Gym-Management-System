package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.MembershipRequestDto;
import com.gymforhealthy.gms.dto.responseDto.MembershipResponseDto;
import com.gymforhealthy.gms.entity.Membership;
import com.gymforhealthy.gms.entity.MembershipPackage;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.repository.MembershipPackageRepository;
import com.gymforhealthy.gms.repository.MembershipRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;
    private final MembershipPackageRepository membershipPackageRepository;
    private final ModelMapper modelMapper;

    @Override
    public MembershipResponseDto save(MembershipRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MembershipPackage membershipPackage = membershipPackageRepository.findById(requestDto.getPackageId())
                .orElseThrow(() -> new RuntimeException("Membership package not found"));

        Membership membership = modelMapper.map(requestDto, Membership.class);
        membership.setUser(user);
        membership.setMembershipPackage(membershipPackage);

        Membership saved = membershipRepository.save(membership);
        return modelMapper.map(saved, MembershipResponseDto.class);
    }

    @Override
    public MembershipResponseDto update(Integer id, MembershipRequestDto requestDto) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found"));

        modelMapper.map(requestDto, membership);

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MembershipPackage membershipPackage = membershipPackageRepository.findById(requestDto.getPackageId())
                .orElseThrow(() -> new RuntimeException("Membership package not found"));

        membership.setUser(user);
        membership.setMembershipPackage(membershipPackage);

        return modelMapper.map(membershipRepository.save(membership), MembershipResponseDto.class);
    }

    @Override
    public void delete(Integer id) {
        membershipRepository.deleteById(id);
    }

    @Override
    public MembershipResponseDto findById(Integer id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
        return modelMapper.map(membership, MembershipResponseDto.class);
    }

    @Override
    public List<MembershipResponseDto> findAll() {
        return membershipRepository.findAll()
                .stream()
                .map(m -> modelMapper.map(m, MembershipResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MembershipResponseDto> findByUserId(Integer userId) {
        return membershipRepository.findByUserId(userId)
                .stream()
                .map(m -> modelMapper.map(m, MembershipResponseDto.class))
                .collect(Collectors.toList());
    }
}
