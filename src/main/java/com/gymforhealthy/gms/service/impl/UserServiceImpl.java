package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.UserRequestDto;
import com.gymforhealthy.gms.dto.responseDto.UserResponseDto;
import com.gymforhealthy.gms.entity.Role;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.exception.ResourceNotFoundException;
import com.gymforhealthy.gms.repository.*;
import com.gymforhealthy.gms.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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
                .map(user -> convertToResponseDto(user))
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
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    private UserResponseDto convertToResponseDto(User user) {
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
        userResponseDto.setRoleName(user.getRole().getName());
        return userResponseDto;
    }
}