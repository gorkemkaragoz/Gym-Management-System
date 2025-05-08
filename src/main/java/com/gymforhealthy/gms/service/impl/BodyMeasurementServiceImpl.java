package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.entity.BodyMeasurement;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.repository.BodyMeasurementRepository;
import com.gymforhealthy.gms.dto.requestDto.BodyMeasurementRequestDto;
import com.gymforhealthy.gms.dto.responseDto.BodyMeasurementResponseDto;
import com.gymforhealthy.gms.service.BodyMeasurementService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BodyMeasurementServiceImpl implements BodyMeasurementService {

    private final BodyMeasurementRepository bodyMeasurementRepository;
    private final ModelMapper modelMapper;

    @Override
    public BodyMeasurementResponseDto save(BodyMeasurementRequestDto dto) {
        BodyMeasurement bodyMeasurement = new BodyMeasurement();
        bodyMeasurement.setWeight(dto.getWeight());
        bodyMeasurement.setHeight(dto.getHeight());
        bodyMeasurement.setCreatedTime(LocalDateTime.now());

        bodyMeasurement.setBmi(calculateBmi(dto.getWeight(), dto.getHeight()));

        User user = new User();
        user.setId(dto.getUserId());
        bodyMeasurement.setUser(user);

        BodyMeasurement saved = bodyMeasurementRepository.save(bodyMeasurement);

        BodyMeasurementResponseDto responseDto = modelMapper.map(saved, BodyMeasurementResponseDto.class);
        responseDto.setUserId(saved.getUser().getId());
        return responseDto;
    }

    @Override
    public List<BodyMeasurementResponseDto> findByUserId(Integer userId) {
        return bodyMeasurementRepository.findByUserId(userId)
                .stream()
                .map(bm -> {
                    BodyMeasurementResponseDto dto = modelMapper.map(bm, BodyMeasurementResponseDto.class);
                    dto.setUserId(bm.getUser().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<BodyMeasurementResponseDto> findAll() {
        return bodyMeasurementRepository.findAll()
                .stream()
                .map(bm -> {
                    BodyMeasurementResponseDto dto = modelMapper.map(bm, BodyMeasurementResponseDto.class);
                    dto.setUserId(bm.getUser().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private BigDecimal calculateBmi(BigDecimal weight, BigDecimal heightCm) {
        if (heightCm == null || heightCm.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
        BigDecimal heightM = heightCm.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return weight.divide(heightM.pow(2), 2, RoundingMode.HALF_UP);
    }
}
