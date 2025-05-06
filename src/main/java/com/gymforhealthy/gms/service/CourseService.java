package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.CourseRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseResponseDto;

import java.util.List;

public interface CourseService {
    CourseResponseDto save(CourseRequestDto courseRequestDto);
    CourseResponseDto update(Integer id, CourseRequestDto courseRequestDto);
    void delete(Integer id);
    CourseResponseDto findById(Integer id);
    List<CourseResponseDto> findAll();
}
