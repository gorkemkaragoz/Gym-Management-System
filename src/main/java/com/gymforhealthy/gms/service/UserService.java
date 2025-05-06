package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.UserRequestDto;
import com.gymforhealthy.gms.dto.responseDto.UserResponseDto;
import com.gymforhealthy.gms.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponseDto save(UserRequestDto userRequestDto);
    UserResponseDto update(Integer id, UserRequestDto userRequestDto);
    void delete(Integer id);
    UserResponseDto findById(Integer id);
    List<UserResponseDto> findAll();
    UserResponseDto findByTcNo(String tcNo);
}
