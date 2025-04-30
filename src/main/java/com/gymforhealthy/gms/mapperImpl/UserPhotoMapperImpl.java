package com.gymforhealthy.gms.mapperImpl;

import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.entity.UserPhoto;
import com.gymforhealthy.gms.mapper.UserPhotoMapper;
import com.gymforhealthy.gms.requestDto.UserPhotoRequestDto;
import com.gymforhealthy.gms.responseDto.UserPhotoResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserPhotoMapperImpl implements UserPhotoMapper {

    @Override
    public UserPhoto toEntity(UserPhotoRequestDto userPhotoRequestDto) {
       if(userPhotoRequestDto == null) return null;

       UserPhoto userPhoto = new UserPhoto();
       userPhoto.setPhoto(userPhotoRequestDto.getPhoto());

        // Yükleme zamanı sistem tarafından atanır
        userPhoto.setUploadedAt(LocalDateTime.now());

        // userId'den sadece ID atanmış bir User nesnesi oluşturup ilişkiyi kuruyoruz
        User user = new User();
        user.setId(userPhotoRequestDto.getUserId());
        userPhoto.setUser(user);

        return userPhoto;

    }

    @Override
    public UserPhotoResponseDto toResponseDto(UserPhoto userPhoto) {
        if (userPhoto == null) return null;

        return UserPhotoResponseDto.builder()
                .id(userPhoto.getId())
                .photo(userPhoto.getPhoto())
                .uploadedAt(userPhoto.getUploadedAt())
                .userId(userPhoto.getUser().getId())
                .build();
    }
}
