package com.gymforhealthy.gms.requestDto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseScheduleRequestDto {

    private LocalDate courseDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer courseId;
    private Integer trainerId;

}
