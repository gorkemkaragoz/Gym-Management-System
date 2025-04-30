package com.gymforhealthy.gms.requestDto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseEnrollmentRequestDto {

    private Integer userId;
    private Integer courseId;

}
