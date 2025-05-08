package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.CourseScheduleRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseScheduleResponseDto;

import java.util.List;

public interface CourseScheduleService {
    CourseScheduleResponseDto save(CourseScheduleRequestDto courseScheduleRequestDto);
    CourseScheduleResponseDto update(Integer id, CourseScheduleRequestDto courseScheduleRequestDto);
    void delete(Integer id);
    List<CourseScheduleResponseDto> findAll();
}
