package com.gymforhealthy.gms.dto.requestDto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String tcNo;
    private Long roleId;

}
