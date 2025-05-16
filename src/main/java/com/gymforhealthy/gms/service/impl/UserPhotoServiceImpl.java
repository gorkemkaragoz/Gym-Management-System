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

@Service
@RequiredArgsConstructor
public class UserPhotoServiceImpl implements UserPhotoService {

    private final UserPhotoRepository userPhotoRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public UserPhotoResponseDto saveUserPhoto(UserPhotoRequestDto userPhotoRequestDto) {

        UserPhoto userPhoto = modelMapper.map(userPhotoRequestDto, UserPhoto.class);
        userPhoto.setId(null);

        User user = userRepository.findById(userPhotoRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id" + userPhotoRequestDto.getUserId()));

        userPhoto.setUser(user);

        userPhoto = userPhotoRepository.save(userPhoto);
        return convertToResponseDto(userPhoto);
    }

    @Override
    public UserPhotoResponseDto updateUserPhoto(Long id, UserPhotoRequestDto userPhotoRequestDto) {

        UserPhoto userPhoto = userPhotoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id" +id));

        modelMapper.map(userPhotoRequestDto, userPhoto);

        User user = userRepository.findById(userPhotoRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id" +userPhotoRequestDto.getUserId()));
        userPhoto.setUser(user);

        userPhoto = userPhotoRepository.save(userPhoto);
        return convertToResponseDto(userPhoto);
    }

    @Override
    public UserPhotoResponseDto findUserPhotoById(Long id) {
        UserPhoto userPhoto = userPhotoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserPhoto not found with id: " + id));
        return convertToResponseDto(userPhoto);
    }

    @Override
    public void deleteUserPhoto(Long id) {
        UserPhoto userPhoto = userPhotoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserPhoto not found with id: " + id));

        userPhotoRepository.delete(userPhoto);
    }

    private UserPhotoResponseDto convertToResponseDto(UserPhoto userPhoto) {
        UserPhotoResponseDto userPhotoResponseDto = modelMapper.map(userPhoto, UserPhotoResponseDto.class);
        userPhotoResponseDto.setUserId(userPhoto.getUser().getId());
        return userPhotoResponseDto;
    }

}
