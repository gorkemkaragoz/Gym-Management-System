package com.gymforhealthy.gms.dto.responseDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseEnrollmentResponseDto {

    private Long id;
    private Long userId;
    private Long courseId;
    private String status; // ACTIVE, COMPLETED, CANCELLED, WAITING

}
