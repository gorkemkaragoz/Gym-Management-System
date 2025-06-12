package com.gymforhealthy.gms.dto.responseDto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserManagementResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String tcNo;
    private String roleName;

    // Membership bilgileri (sadece member ise dolu olur)
    private String packageName;
    private String membershipStatus;

    // Trainer bilgileri (sadece trainer ise dolu olur)
    private String certificateName;
    private String issuedBy;
    private LocalDate issuedDate;
}
