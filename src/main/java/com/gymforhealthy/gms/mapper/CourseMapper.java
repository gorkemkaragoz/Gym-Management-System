package com.gymforhealthy.gms.mapper;

import com.gymforhealthy.gms.entity.Course;
import com.gymforhealthy.gms.requestDto.CourseRequestDto;
import com.gymforhealthy.gms.responseDto.CourseResponseDto;

public interface CourseMapper {

    // DTO'dan entity'ye dönüşüm
    Course toEntity(CourseRequestDto courseRequestDto);

    // entity'den DTO'ya dönüşüm
    CourseResponseDto toResponseDto(Course course);

}
