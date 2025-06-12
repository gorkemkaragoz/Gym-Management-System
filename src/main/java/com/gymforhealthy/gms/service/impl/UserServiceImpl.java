package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.UserManagementRequestDto;
import com.gymforhealthy.gms.dto.requestDto.UserRequestDto;
import com.gymforhealthy.gms.dto.responseDto.UserManagementResponseDto;
import com.gymforhealthy.gms.dto.responseDto.UserResponseDto;
import com.gymforhealthy.gms.entity.*;
import com.gymforhealthy.gms.exception.ResourceNotFoundException;
import com.gymforhealthy.gms.repository.*;
import com.gymforhealthy.gms.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TrainerCertificateRepository trainerCertificateRepository;
    private final MembershipPackageRepository membershipPackageRepository;
    private final MembershipRepository membershipRepository;

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        User user = modelMapper.map(userRequestDto, User.class);

        user.setId(null);
        Role role = roleRepository.findById(userRequestDto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + userRequestDto.getRoleId()));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setAccountLocked(false);

        user = userRepository.save(user);
        return convertToResponseDto(user);
    }

    @Override
    public UserResponseDto createUserByAdmin(UserManagementRequestDto requestDto) {
        // 1. Rolü getir
        Role role = roleRepository.findById(requestDto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + requestDto.getRoleId()));

        // 2. Kullanıcı oluştur
        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setTcNo(requestDto.getTcNo());
        user.setGender(requestDto.getGender());
        user.setRole(role);
        user.setAccountLocked(false);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        // 3. Kullanıcıyı kaydet (ID oluşması için)
        user = userRepository.save(user);

        // 4. Eğer Trainer ise sertifika ekle
        if (role.getName().equalsIgnoreCase("TRAINER")
                && requestDto.getCertificateName() != null
                && requestDto.getIssuedBy() != null
                && requestDto.getIssuedDate() != null) {

            TrainerCertificate certificate = TrainerCertificate.builder()
                    .user(user)
                    .certificateName(requestDto.getCertificateName())
                    .issuedBy(requestDto.getIssuedBy())
                    .issuedDate(LocalDate.parse(requestDto.getIssuedDate()))
                    .build();

            trainerCertificateRepository.save(certificate);
        }

        // 5. Eğer Member ise üyelik ekle
        if (role.getName().equalsIgnoreCase("MEMBER")
                && requestDto.getPackageName() != null
                && requestDto.getMembershipStatus() != null) {

            MembershipPackage membershipPackage = membershipPackageRepository.findByName(requestDto.getPackageName())
                    .orElseThrow(() -> new ResourceNotFoundException("Package not found: " + requestDto.getPackageName()));

            Membership membership = Membership.builder()
                    .user(user)
                    .membershipPackage(membershipPackage)
                    .membershipStatus(Membership.MembershipStatus.valueOf(requestDto.getMembershipStatus().toUpperCase()))
                    .build();

            membershipRepository.save(membership);
        }

        // 6. Response DTO dön
        return convertToResponseDto(user);
    }

    @Override
    public UserManagementResponseDto updatePartialUser(Long id, UserManagementRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Güncellenecek alanlar varsa kontrol ederek set et
        if (requestDto.getFirstName() != null) user.setFirstName(requestDto.getFirstName());
        if (requestDto.getLastName() != null) user.setLastName(requestDto.getLastName());
        if (requestDto.getEmail() != null) user.setEmail(requestDto.getEmail());
        if (requestDto.getTcNo() != null) user.setTcNo(requestDto.getTcNo());
        if (requestDto.getGender() != null) user.setGender(requestDto.getGender());
        if (requestDto.getPassword() != null) user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        if (requestDto.getRoleId() != null) {
            Role role = roleRepository.findById(requestDto.getRoleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + requestDto.getRoleId()));
            user.setRole(role);
        }

        user = userRepository.save(user);

        // Eğitmen ise sertifikayı güncelle
        if (user.getRole().getName().equalsIgnoreCase("TRAINER")) {
            if (requestDto.getCertificateName() != null || requestDto.getIssuedBy() != null || requestDto.getIssuedDate() != null) {
                TrainerCertificate cert = user.getTrainerCertificates().stream().findFirst()
                        .orElse(new TrainerCertificate());

                cert.setUser(user);
                if (requestDto.getCertificateName() != null) cert.setCertificateName(requestDto.getCertificateName());
                if (requestDto.getIssuedBy() != null) cert.setIssuedBy(requestDto.getIssuedBy());
                if (requestDto.getIssuedDate() != null) cert.setIssuedDate(LocalDate.parse(requestDto.getIssuedDate()));

                trainerCertificateRepository.save(cert);
            }
        }

        // Üye ise üyelik güncelle
        if (user.getRole().getName().equalsIgnoreCase("MEMBER")) {
            if (requestDto.getPackageName() != null || requestDto.getMembershipStatus() != null) {
                Membership membership = user.getMemberships().stream().findFirst()
                        .orElse(new Membership());
                membership.setUser(user);

                if (requestDto.getPackageName() != null) {
                    MembershipPackage mp = membershipPackageRepository.findByName(requestDto.getPackageName())
                            .orElseThrow(() -> new ResourceNotFoundException("Package not found"));
                    membership.setMembershipPackage(mp);
                }

                if (requestDto.getMembershipStatus() != null) {
                    membership.setMembershipStatus(Membership.MembershipStatus.valueOf(requestDto.getMembershipStatus().toUpperCase()));
                }

                membershipRepository.save(membership);
            }
        }

        return convertToManagementDto(user);
    }

    @Override
    public List<UserManagementResponseDto> findAllByRoleName(String roleName) {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().getName().equalsIgnoreCase(roleName))
                .map(this::convertToManagementDto)
                .toList();
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        modelMapper.map(userRequestDto, user);
        Role role = roleRepository.findById(userRequestDto.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + userRequestDto.getRoleId()));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user = userRepository.save(user);

        return convertToResponseDto(user);
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return convertToResponseDto(user);
    }

    @Override
    public UserResponseDto findByTcNo(String tcNo) {
        User user = userRepository.findByTcNo(tcNo);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with TC No: " + tcNo);
        }
        return convertToResponseDto(user);
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return convertToResponseDto(user);
    }

    @Override
    public UserManagementResponseDto findByEmaill(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return convertToManagementDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    @Override
    public void changePassword(String email, String currentPassword, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new BadCredentialsException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public List<UserManagementResponseDto> getAllMembersAndTrainers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .filter(user -> user.getRole().getName().equalsIgnoreCase("Member") ||
                        user.getRole().getName().equalsIgnoreCase("Trainer"))
                .map(user -> {
                    var builder = UserManagementResponseDto.builder()
                            .id(user.getId())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .email(user.getEmail())
                            .gender(user.getGender())
                            .tcNo(user.getTcNo())
                            .roleName(user.getRole().getName());

                    if (user.getRole().getName().equalsIgnoreCase("Trainer")
                            && user.getTrainerCertificates() != null
                            && !user.getTrainerCertificates().isEmpty()) {
                        var cert = user.getTrainerCertificates().get(0);
                        builder.certificateName(cert.getCertificateName())
                                .issuedBy(cert.getIssuedBy())
                                .issuedDate(cert.getIssuedDate());
                    }

                    if (user.getRole().getName().equalsIgnoreCase("Member")
                            && user.getMemberships() != null
                            && !user.getMemberships().isEmpty()) {
                        var membership = user.getMemberships().get(0);
                        builder.packageName(membership.getMembershipPackage().getName())
                                .membershipStatus(membership.getMembershipStatus().toString());
                    }

                    return builder.build();
                }).collect(Collectors.toList());
    }

    private UserResponseDto convertToResponseDto(User user) {
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
        userResponseDto.setRoleName(user.getRole().getName());
        return userResponseDto;
    }

    private UserManagementResponseDto convertToManagementDto(User user) {
        var builder = UserManagementResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .gender(user.getGender())
                .tcNo(user.getTcNo())
                .roleName(user.getRole().getName());

        if (user.getRole().getName().equalsIgnoreCase("Trainer")
                && user.getTrainerCertificates() != null
                && !user.getTrainerCertificates().isEmpty()) {
            var cert = user.getTrainerCertificates().get(0);
            builder.certificateName(cert.getCertificateName())
                    .issuedBy(cert.getIssuedBy())
                    .issuedDate(cert.getIssuedDate());
        }

        if (user.getRole().getName().equalsIgnoreCase("Member")
                && user.getMemberships() != null
                && !user.getMemberships().isEmpty()) {
            var membership = user.getMemberships().get(0);
            builder.packageName(membership.getMembershipPackage().getName())
                    .membershipStatus(membership.getMembershipStatus().toString());
        }

        return builder.build();
    }

}