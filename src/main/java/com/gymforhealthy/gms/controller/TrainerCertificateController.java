package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.TrainerCertificateRequestDto;
import com.gymforhealthy.gms.dto.responseDto.TrainerCertificateResponseDto;
import com.gymforhealthy.gms.service.TrainerCertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trainer-certificates")
@RequiredArgsConstructor
public class TrainerCertificateController {

    private final TrainerCertificateService trainerCertificateService;

    @PostMapping
    public ResponseEntity<TrainerCertificateResponseDto> save(@RequestBody TrainerCertificateRequestDto trainerCertificateRequestDto) {
        return ResponseEntity.ok(trainerCertificateService.saveTrainerCertificate(trainerCertificateRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerCertificateResponseDto> update(@PathVariable Long id, @RequestBody TrainerCertificateRequestDto trainerCertificateRequestDto) {
        return ResponseEntity.ok(trainerCertificateService.updateTrainerCertificate(id, trainerCertificateRequestDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TrainerCertificateResponseDto>> findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(trainerCertificateService.findTrainerCertificatesByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerCertificateResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(trainerCertificateService.findTrainerCertificateById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trainerCertificateService.deleteTrainerCertificate(id);
        return ResponseEntity.noContent().build();
    }
}