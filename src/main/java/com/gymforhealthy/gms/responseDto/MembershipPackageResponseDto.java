package com.gymforhealthy.gms.responseDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipPackageResponseDto {

    private Integer id;
    private String name;
    private Integer packageTotalHour;
    private Boolean isUnlimited;


}
