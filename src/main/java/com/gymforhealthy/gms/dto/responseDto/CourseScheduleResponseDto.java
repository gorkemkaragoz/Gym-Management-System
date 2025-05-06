package com.gymforhealthy.gms.dto.responseDto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseScheduleResponseDto {

    private Integer id;
    private LocalDate courseDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer courseId;
    private Integer trainerId;

}
