package com.gymforhealthy.gms.responseDto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String tcNo;
    private String roleName;

}
