package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.BodyMeasurementRequestDto;
import com.gymforhealthy.gms.dto.responseDto.BodyMeasurementResponseDto;
import com.gymforhealthy.gms.service.BodyMeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/body-measurements")
@RequiredArgsConstructor
public class BodyMeasurementController {

    private final BodyMeasurementService bodyMeasurementService;

    @PostMapping
    public ResponseEntity<BodyMeasurementResponseDto> create(@RequestBody BodyMeasurementRequestDto dto) {
        return ResponseEntity.ok(bodyMeasurementService.save(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BodyMeasurementResponseDto>> getByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(bodyMeasurementService.findByUserId(userId));
    }

}
