package com.gymforhealthy.gms.mapperImpl;

import com.gymforhealthy.gms.entity.Role;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.mapper.UserMapper;
import com.gymforhealthy.gms.requestDto.UserRequestDto;
import com.gymforhealthy.gms.responseDto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper{

    @Override
    public User toEntity(UserRequestDto userRequestDto) {
        if (userRequestDto == null) return null;

        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setGender(userRequestDto.getGender());
        user.setTcNo(userRequestDto.getTcNo());

        Role role = new Role();
        role.setId(userRequestDto.getRoleId());
        user.setRole(role);

        return user;

    }

    @Override
    public UserResponseDto toResponseDto(User user) {
       if (user == null) return null;

       return UserResponseDto.builder()
               .id(user.getId())
               .email(user.getEmail())
               .firstName(user.getFirstName())
               .lastName(user.getLastName())
               .gender(user.getGender())
               .tcNo(user.getTcNo())
               .roleName(user.getRole() != null ? user.getRole().getName() : null)
               .build();

    }

}
