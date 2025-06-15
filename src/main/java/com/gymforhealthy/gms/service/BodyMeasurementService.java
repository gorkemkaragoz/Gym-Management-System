package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.BodyMeasurementRequestDto;
import com.gymforhealthy.gms.dto.responseDto.BodyMeasurementResponseDto;
import org.springframework.security.core.Authentication;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface BodyMeasurementService {
    BodyMeasurementResponseDto saveBodyMeasurement(BodyMeasurementRequestDto bodyMeasurementRequestDto, Authentication authentication) throws AccessDeniedException;    BodyMeasurementResponseDto updateBodyMeasurement(Long id, BodyMeasurementRequestDto bodyMeasurementRequestDto);
    BodyMeasurementResponseDto findLastMeasurementByUserId(Long userId);
    List<BodyMeasurementResponseDto> findAllBodyMeasurement();
    BodyMeasurementResponseDto findBodyMeasurementById(Long id);
    void deleteBodyMeasurement(Long id);
}
