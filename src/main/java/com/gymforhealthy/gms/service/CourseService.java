package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.CourseRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseResponseDto;

import java.util.List;

public interface CourseService {
    CourseResponseDto saveCourse(CourseRequestDto courseRequestDto);
    CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto);
    List<CourseResponseDto> findAllCourse();
    CourseResponseDto findCourseById(Long id);
    void deleteCourse(Long id);
}
