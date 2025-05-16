package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.TrainerCertificateRequestDto;
import com.gymforhealthy.gms.dto.responseDto.TrainerCertificateResponseDto;

import java.util.List;

public interface TrainerCertificateService {

    TrainerCertificateResponseDto saveTrainerCertificate(TrainerCertificateRequestDto trainerCertificateRequestDto);
    TrainerCertificateResponseDto updateTrainerCertificate(Long id, TrainerCertificateRequestDto trainerCertificateRequestDto);
    List<TrainerCertificateResponseDto> findTrainerCertificatesByUserId(Long userId);
    TrainerCertificateResponseDto findTrainerCertificateById(Long id);
    void deleteTrainerCertificate(Long id);

}