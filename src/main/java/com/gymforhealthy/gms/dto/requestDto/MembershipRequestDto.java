package com.gymforhealthy.gms.dto.requestDto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipRequestDto {

    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;
    private Long packageId;

}
