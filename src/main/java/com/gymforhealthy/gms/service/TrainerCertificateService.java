package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.TrainerCertificateRequestDto;
import com.gymforhealthy.gms.dto.responseDto.TrainerCertificateResponseDto;

import java.util.List;

public interface TrainerCertificateService {
    TrainerCertificateResponseDto save(TrainerCertificateRequestDto trainerCertificateRequestDto);
    List<TrainerCertificateResponseDto> findByTrainerId(Integer trainerId);
    void delete(Integer id);
    List<TrainerCertificateResponseDto> findByUserId(Integer userId);
}
