package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.BodyMeasurementRequestDto;
import com.gymforhealthy.gms.dto.responseDto.BodyMeasurementResponseDto;
import com.gymforhealthy.gms.service.BodyMeasurementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/body-measurement")
@AllArgsConstructor
public class BodyMeasurementController {

    private BodyMeasurementService bodyMeasurementService;

    @PostMapping
    public ResponseEntity<BodyMeasurementResponseDto> save(@RequestBody BodyMeasurementRequestDto bodyMeasurementRequestDto) {
        return ResponseEntity.ok(bodyMeasurementService.saveBodyMeasurement(bodyMeasurementRequestDto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<BodyMeasurementResponseDto> update(@PathVariable Long id, @RequestBody BodyMeasurementRequestDto bodyMeasurementRequestDto) {
        return ResponseEntity.ok(bodyMeasurementService.updateBodyMeasurement(id, bodyMeasurementRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<BodyMeasurementResponseDto>> getAll() {
        return ResponseEntity.ok(bodyMeasurementService.findAllBodyMeasurement());
    }

    @GetMapping("/last/{userId}")
    public ResponseEntity<BodyMeasurementResponseDto> getLastMeasurementForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bodyMeasurementService.findLastMeasurementByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BodyMeasurementResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bodyMeasurementService.findBodyMeasurementById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BodyMeasurementResponseDto> delete(@PathVariable Long id) {
        bodyMeasurementService.deleteBodyMeasurement(id);
        return ResponseEntity.noContent().build();
    }

}
