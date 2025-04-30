package com.gymforhealthy.gms.mapperImpl;

import com.gymforhealthy.gms.entity.BodyMeasurement;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.mapper.BodyMeasurementMapper;
import com.gymforhealthy.gms.requestDto.BodyMeasurementRequestDto;
import com.gymforhealthy.gms.responseDto.BodyMeasurementResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BodyMeasurementMapperImpl implements BodyMeasurementMapper {

    @Override
    public BodyMeasurement toEntity(BodyMeasurementRequestDto bodyMeasurementRequestDto) {
        if (bodyMeasurementRequestDto == null) return null;

        BodyMeasurement bodyMeasurement = new BodyMeasurement();
        bodyMeasurement.setWeight(bodyMeasurementRequestDto.getWeight());
        bodyMeasurement.setCreatedTime(LocalDateTime.now());

        User user = new User();
        user.setId(bodyMeasurementRequestDto.getUserId());
        bodyMeasurement.setUser(user);

        return bodyMeasurement;
    }

    @Override
    public BodyMeasurementResponseDto toResponseDto(BodyMeasurement bodyMeasurement) {
        if (bodyMeasurement == null) return null;

        return BodyMeasurementResponseDto.builder()
                .id(bodyMeasurement.getId())
                .weight(bodyMeasurement.getWeight())
                .bmi(bodyMeasurement.getBmi())
                .createdTime(bodyMeasurement.getCreatedTime())
                .userId(bodyMeasurement.getUser().getId())
                .build();
    }
}
