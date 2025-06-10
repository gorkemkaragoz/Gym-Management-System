package com.gymforhealthy.gms.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseWithScheduleRequestDto {
    // CourseRequestDto'dan gelenler
    private String name;
    private Integer maxCapacity;
    private Long trainerId;

    // CourseScheduleRequestDto'dan gelenler
    @Schema(type = "string", example = "2025-05-15")
    private LocalDate courseDate;

    @Schema(type = "string", example = "10:00")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @Schema(type = "string", example = "15:00")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;
}