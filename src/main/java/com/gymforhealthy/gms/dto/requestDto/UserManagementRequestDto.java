// src/main/java/com/gymforhealthy/gms/dto/requestDto/UserManagementRequestDto.java
package com.gymforhealthy.gms.dto.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserManagementRequestDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String tcNo;
    private Long roleId;

    // Optional alanlar (sadece rol Trainer ise dolu olacak)
    private String certificateName;
    private String issuedBy;
    private String issuedDate;

    // Optional alanlar (sadece rol Member ise dolu olacak)
    private String packageName;
    private String membershipStatus;

    private String photoUrl;
}