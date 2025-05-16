package com.gymforhealthy.gms.dto.requestDto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseScheduleRequestDto {

    @Schema(type = "string", example = "2025-05-15")
    private LocalDate courseDate;

    @Schema(type = "string", example = "10:00")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @Schema(type = "string", example = "15:00")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    private Long courseId;

    private Long trainerId;
}
