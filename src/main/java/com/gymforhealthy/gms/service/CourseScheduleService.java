package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.CourseScheduleRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseScheduleResponseDto;

import java.util.List;

public interface CourseScheduleService {
    CourseScheduleResponseDto saveCourseSchedule(CourseScheduleRequestDto courseScheduleRequestDto);
    CourseScheduleResponseDto updateCourseSchedule(Long id, CourseScheduleRequestDto courseScheduleRequestDto);
    List<CourseScheduleResponseDto> findAllCourseSchedule();
    CourseScheduleResponseDto findCourseScheduleById(Long id);
    void delete(Long id);
}
