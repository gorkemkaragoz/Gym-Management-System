package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.BodyMeasurementRequestDto;
import com.gymforhealthy.gms.dto.responseDto.BodyMeasurementResponseDto;

import java.util.List;

public interface BodyMeasurementService {
    BodyMeasurementResponseDto save(BodyMeasurementRequestDto dto);
    List<BodyMeasurementResponseDto> findByUserId(Integer userId);
    List<BodyMeasurementResponseDto> findAll();
}
