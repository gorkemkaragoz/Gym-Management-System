package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.UserPhotoRequestDto;
import com.gymforhealthy.gms.dto.responseDto.UserPhotoResponseDto;

public interface UserPhotoService {

    UserPhotoResponseDto saveUserPhoto(UserPhotoRequestDto userPhotoRequestDto);
    UserPhotoResponseDto updateUserPhoto(Long id,UserPhotoRequestDto userPhotoRequestDto);
    UserPhotoResponseDto findUserPhotoById(Long id);
    void deleteUserPhoto(Long id);

}
