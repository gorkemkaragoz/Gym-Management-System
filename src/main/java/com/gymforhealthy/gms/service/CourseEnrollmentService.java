package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.CourseEnrollmentRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseEnrollmentResponseDto;

import java.util.List;

public interface CourseEnrollmentService {

    CourseEnrollmentResponseDto saveCourseEnrollment(CourseEnrollmentRequestDto courseEnrollmentRequestDto);
    CourseEnrollmentResponseDto updateCourseEnrollment(Long id, CourseEnrollmentRequestDto courseEnrollmentRequestDto);
    List<CourseEnrollmentResponseDto> findAllCourseEnrollment();
    CourseEnrollmentResponseDto findCourseEnrollmentById (Long id);
    void delete(Long id);

}
