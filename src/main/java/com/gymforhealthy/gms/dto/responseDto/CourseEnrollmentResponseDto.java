package com.gymforhealthy.gms.dto.responseDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseEnrollmentResponseDto {

    private Integer id;
    private Integer userId;
    private Integer courseId;
    private String status; // ACTIVE, COMPLETED, CANCELLED, WAITING

}
