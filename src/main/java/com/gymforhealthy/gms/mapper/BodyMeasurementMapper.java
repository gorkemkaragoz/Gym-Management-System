package com.gymforhealthy.gms.mapper;

import com.gymforhealthy.gms.entity.BodyMeasurement;
import com.gymforhealthy.gms.requestDto.BodyMeasurementRequestDto;
import com.gymforhealthy.gms.responseDto.BodyMeasurementResponseDto;

public interface BodyMeasurementMapper {

    BodyMeasurement toEntity(BodyMeasurementRequestDto bodyMeasurementRequestDto);

    BodyMeasurementResponseDto toResponseDto(BodyMeasurement bodyMeasurement);

}
