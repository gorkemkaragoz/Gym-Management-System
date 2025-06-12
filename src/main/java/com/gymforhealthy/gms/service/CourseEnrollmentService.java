package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.CourseEnrollmentRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseEnrollmentResponseDto;

import java.util.List;

public interface CourseEnrollmentService {

    CourseEnrollmentResponseDto saveCourseEnrollment(CourseEnrollmentRequestDto courseEnrollmentRequestDto);

    // Admin tarafından kurs kayıt silme (Enroll ID ile)
    void deleteCourseEnrollment(Long enrollmentId); // ❗ burada `enrollmentId` olmalı

    // Üye kendi kaydını iptal eder
    void cancelEnrollment(Long scheduleId);

    List<CourseEnrollmentResponseDto> getMyEnrollments();
}
