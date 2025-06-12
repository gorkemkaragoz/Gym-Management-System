package com.gymforhealthy.gms.dto.responseDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseEnrollmentResponseDto {
    private Long id;             // ← save için lazım
    private Long userId;
    private Long courseId;
    private Long scheduleId;

    private String courseName;
    private String courseDate;
    private String startTime;
    private String endTime;

    private String status;       // ← ACTIVE, CANCELLED, vs.
}
