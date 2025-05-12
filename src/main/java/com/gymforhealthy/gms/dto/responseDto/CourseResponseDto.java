package com.gymforhealthy.gms.dto.responseDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponseDto {

    private Long id;
    private String name;
    private Integer maxCapacity;
    private Long trainerId;
}
