package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.CourseEnrollmentRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseEnrollmentResponseDto;

public interface CourseEnrollmentService {
    CourseEnrollmentResponseDto enroll(CourseEnrollmentRequestDto enrollmentRequestDto);
    void cancelEnrollment(Integer enrollmentId);
    CourseEnrollmentResponseDto findByUserIdAndCourseId(Integer userId, Integer courseId);
}
