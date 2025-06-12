    package com.gymforhealthy.gms.service;

    import com.gymforhealthy.gms.dto.requestDto.UserManagementRequestDto;
    import com.gymforhealthy.gms.dto.requestDto.UserRequestDto;
    import com.gymforhealthy.gms.dto.responseDto.UserManagementResponseDto;
    import com.gymforhealthy.gms.dto.responseDto.UserResponseDto;

    import java.util.List;

    public interface UserService {
        UserResponseDto saveUser(UserRequestDto userRequestDto);
        UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);
        List<UserResponseDto> findAllUsers();
        UserResponseDto findUserById(Long id);
        UserResponseDto findByTcNo(String tcNo);
        UserResponseDto findByEmail(String email);
        UserManagementResponseDto findByEmaill(String email);
        void deleteUser(Long id);
        void changePassword(String email, String currentPassword, String newPassword);
        List<UserManagementResponseDto> getAllMembersAndTrainers();
        UserResponseDto createUserByAdmin(UserManagementRequestDto requestDto);
        List<UserResponseDto> findAllByRoleName(String roleName);
        UserManagementResponseDto updatePartialUser(Long id, UserManagementRequestDto requestDto);

    }
