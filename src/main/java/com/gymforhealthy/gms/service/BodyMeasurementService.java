package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.BodyMeasurementRequestDto;
import com.gymforhealthy.gms.dto.responseDto.BodyMeasurementResponseDto;

import java.util.List;

public interface BodyMeasurementService {
    BodyMeasurementResponseDto saveBodyMeasurement(BodyMeasurementRequestDto bodyMeasurementRequestDto);
    BodyMeasurementResponseDto updateBodyMeasurement(Long id, BodyMeasurementRequestDto bodyMeasurementRequestDto);
    BodyMeasurementResponseDto findLastMeasurementByUserId(Long userId);
    List<BodyMeasurementResponseDto> findAllBodyMeasurement();
    BodyMeasurementResponseDto findBodyMeasurementById(Long id);
    void deleteBodyMeasurement(Long id);
}
