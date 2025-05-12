    package com.gymforhealthy.gms.service;

    import com.gymforhealthy.gms.dto.requestDto.UserRequestDto;
    import com.gymforhealthy.gms.dto.responseDto.UserResponseDto;

    import java.util.List;

    public interface UserService {
        UserResponseDto saveUser(UserRequestDto userRequestDto);
        UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);
        List<UserResponseDto> findAllUsers();
        UserResponseDto findUserById(Long id);
        UserResponseDto findByTcNo(String tcNo);
        void deleteUser(Long id);
    }
