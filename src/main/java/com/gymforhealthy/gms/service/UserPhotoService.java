package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.UserPhotoRequestDto;
import com.gymforhealthy.gms.dto.responseDto.UserPhotoResponseDto;

import java.util.List;

public interface UserPhotoService {
    UserPhotoResponseDto upload(UserPhotoRequestDto userPhotoRequestDto);
    List<UserPhotoResponseDto> findByUserId(Integer userId);
    void delete(Integer photoId);
    UserPhotoResponseDto findById(Integer photoId);
}
