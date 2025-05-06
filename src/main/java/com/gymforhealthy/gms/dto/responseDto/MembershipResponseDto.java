package com.gymforhealthy.gms.dto.responseDto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipResponseDto {

    private Integer id;
    private Integer remainingHours;
    private LocalDate startDate;
    private LocalDate endDate;
    private String membershipStatus;
    private Integer userId;
    private String packageName;

}
