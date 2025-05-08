package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.UserPhotoRequestDto;
import com.gymforhealthy.gms.dto.responseDto.UserPhotoResponseDto;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.entity.UserPhoto;
import com.gymforhealthy.gms.repository.UserPhotoRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.UserPhotoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserPhotoServiceImpl implements UserPhotoService {

    private final UserPhotoRepository userPhotoRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserPhotoResponseDto upload(UserPhotoRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserPhoto photo = new UserPhoto();
        photo.setPhoto(requestDto.getPhoto());
        photo.setUploadedAt(LocalDateTime.now());
        photo.setUser(user);

        UserPhoto savedPhoto = userPhotoRepository.save(photo);
        return modelMapper.map(savedPhoto, UserPhotoResponseDto.class);
    }

    @Override
    public List<UserPhotoResponseDto> findByUserId(Integer userId) {
        return userPhotoRepository.findByUserId(userId).stream()
                .map(photo -> {
                    UserPhotoResponseDto dto = modelMapper.map(photo, UserPhotoResponseDto.class);
                    dto.setUserId(photo.getUser().getId()); // BU ÇOK KRİTİK
                    return dto;
                })
                .collect(Collectors.toList());
    }


    @Override
    public void delete(Integer photoId) {
        userPhotoRepository.deleteById(photoId);
    }

    @Override
    public UserPhotoResponseDto findById(Integer photoId) {
        UserPhoto photo = userPhotoRepository.findById(photoId)
                .orElseThrow(() -> new RuntimeException("Photo not found"));
        return modelMapper.map(photo, UserPhotoResponseDto.class);
    }
}
