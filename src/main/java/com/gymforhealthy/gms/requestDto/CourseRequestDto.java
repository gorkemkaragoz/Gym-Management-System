package com.gymforhealthy.gms.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequestDto {

    private String name;
    private Integer maxCapacity;
    private Integer trainerId;
}
