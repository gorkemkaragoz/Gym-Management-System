package com.gymforhealthy.gms.mapper;


import com.gymforhealthy.gms.requestDto.UserRequestDto;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.responseDto.UserResponseDto;

public interface UserMapper {

    // Frontend'den gelen kullanıcı kaydı verisini (RequestDto) → Entity objesine çevirir.
    User toEntity(UserRequestDto userRequestDto);

    // Veritabanından gelen User entity’sini → API ile dış dünyaya dönülecek UserResponseDto'ya çevirir.
    UserResponseDto toResponseDto(User user);

}
