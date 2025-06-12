// src/main/java/com/gymforhealthy/gms/dto/responseDto/CourseScheduleOverviewResponseDto.java
package com.gymforhealthy.gms.dto.responseDto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseScheduleOverviewResponseDto {
    private Long scheduleId;

    // Kurs bilgileri
    private String courseName;
    private Integer maxCapacity;
    private int currentStudentCount;

    // Eğitmen bilgisi
    private Long trainerId;
    private String trainerName;

    // Program bilgisi
    private LocalDate courseDate;
    private LocalTime startTime;
    private LocalTime endTime;
}