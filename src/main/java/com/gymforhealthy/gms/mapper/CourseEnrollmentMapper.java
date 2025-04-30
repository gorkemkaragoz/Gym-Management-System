package com.gymforhealthy.gms.mapper;

import com.gymforhealthy.gms.entity.CourseEnrollment;
import com.gymforhealthy.gms.requestDto.CourseEnrollmentRequestDto;
import com.gymforhealthy.gms.responseDto.CourseEnrollmentResponseDto;

public interface CourseEnrollmentMapper {

    // DTO'dan entity'ye dönüşüm
    CourseEnrollment toEntity(CourseEnrollmentRequestDto courseEnrollmentRequestDto);

    // entity'den DTO'ya dönüşüm
    CourseEnrollmentResponseDto toResponseDto(CourseEnrollment courseEnrollment);

}
