package com.gymforhealthy.gms.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserWithPhotoResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String roleName;
    private String tcNo;
    private String photoUrl;
}