package com.gymforhealthy.gms.dto.responseDto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String tcNo;
    private Boolean accountLocked;
    private String roleName;

}
