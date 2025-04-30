package com.gymforhealthy.gms.mapper;

import com.gymforhealthy.gms.entity.CourseSchedule;
import com.gymforhealthy.gms.requestDto.CourseScheduleRequestDto;
import com.gymforhealthy.gms.responseDto.CourseScheduleResponseDto;

public interface CourseScheduleMapper {

    // DTO'dan entity'ye dönüşüm
    CourseSchedule toEntity(CourseScheduleRequestDto courseScheduleRequestDto);

    // entity'den DTO'ya dönüşüm
    CourseScheduleResponseDto toResponseDto(CourseSchedule courseSchedule);

}
