package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.UserRequestDto;
import com.gymforhealthy.gms.dto.responseDto.UserResponseDto;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.entity.Role;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.repository.RoleRepository;
import com.gymforhealthy.gms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDto save(UserRequestDto requestDto) {
        User user = modelMapper.map(requestDto, User.class);
        Role role = roleRepository.findById(requestDto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        return modelMapper.map(userRepository.save(user), UserResponseDto.class);
    }

    @Override
    public UserResponseDto update(Integer id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        modelMapper.map(requestDto, user);
        Role role = roleRepository.findById(requestDto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        return modelMapper.map(userRepository.save(user), UserResponseDto.class);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto findById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto findByTcNo(String tcNo) {
        User user = userRepository.findByTcNo(tcNo)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserResponseDto.class);
    }

}
