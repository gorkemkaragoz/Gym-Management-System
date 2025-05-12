package com.gymforhealthy.gms.dto.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseEnrollmentRequestDto {
    private Long userId;
    private Long courseId;
}
