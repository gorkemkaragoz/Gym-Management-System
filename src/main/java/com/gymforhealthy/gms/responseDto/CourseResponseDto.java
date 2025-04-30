package com.gymforhealthy.gms.responseDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponseDto {

    private Integer id;
    private String name;
    private Integer maxCapacity;
    private Integer trainerId;
}
