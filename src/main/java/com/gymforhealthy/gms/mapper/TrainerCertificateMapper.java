package com.gymforhealthy.gms.mapper;


import com.gymforhealthy.gms.entity.TrainerCertificate;
import com.gymforhealthy.gms.requestDto.TrainerCertificateRequestDto;
import com.gymforhealthy.gms.responseDto.TrainerCertificateResponseDto;

public interface TrainerCertificateMapper {

    // DTO'dan entity'ye dönüşüm
    TrainerCertificate toEntity(TrainerCertificateRequestDto trainerCertificateRequestDto);

    // entity'den DTO'ya dönüşüm (değişken adı: trainerCertificate)
    TrainerCertificateResponseDto toResponseDto(TrainerCertificate trainerCertificate);

}
