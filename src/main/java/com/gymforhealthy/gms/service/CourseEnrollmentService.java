package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.CourseEnrollmentRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseEnrollmentResponseDto;
import com.gymforhealthy.gms.entity.CourseEnrollment;

import java.util.Optional;

public interface CourseEnrollmentService {

    void enrollUserToCourse(Integer userId, Integer courseId);
    void cancelEnrollmentByUserAndCourse(Integer userId, Integer courseId);
    boolean isUserEnrolled(Integer userId, Integer courseId);
}
