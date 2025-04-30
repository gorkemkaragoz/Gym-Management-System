package com.gymforhealthy.gms.mapper;

import com.gymforhealthy.gms.requestDto.UserPhotoRequestDto;
import com.gymforhealthy.gms.entity.UserPhoto;
import com.gymforhealthy.gms.responseDto.UserPhotoResponseDto;

public interface UserPhotoMapper {

    // Frontend'den gelen DTO'dan Entity üretir
    UserPhoto toEntity(UserPhotoRequestDto userPhotoRequestDto);

    // Veritabanındaki Entity'den dış dünyaya sunulacak DTO üretir
    UserPhotoResponseDto toResponseDto(UserPhoto userPhoto);

}
