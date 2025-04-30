package com.gymforhealthy.gms.mapperImpl;


import com.gymforhealthy.gms.entity.TrainerCertificate;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.mapper.TrainerCertificateMapper;
import com.gymforhealthy.gms.requestDto.TrainerCertificateRequestDto;
import com.gymforhealthy.gms.responseDto.TrainerCertificateResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TrainerCertificateMapperImpl implements TrainerCertificateMapper {
    @Override
    public TrainerCertificate toEntity(TrainerCertificateRequestDto trainerCertificateRequestDto) {
       if(trainerCertificateRequestDto == null) return null;

        TrainerCertificate trainerCertificate = new TrainerCertificate();
        trainerCertificate.setCertificateName(trainerCertificateRequestDto.getCertificateName());
        trainerCertificate.setIssuedBy(trainerCertificateRequestDto.getIssuedBy());
        trainerCertificate.setIssuedDate(trainerCertificateRequestDto.getIssuedDate());

        User user = new User();
        user.setId(trainerCertificateRequestDto.getUserId());
        trainerCertificate.setUser(user);

        return trainerCertificate;

    }

    @Override
    public TrainerCertificateResponseDto toResponseDto(TrainerCertificate trainerCertificate) {
        if (trainerCertificate == null ) return null;

        return TrainerCertificateResponseDto.builder()
                .id(trainerCertificate.getId())
                .certificateName(trainerCertificate.getCertificateName())
                .issuedBy(trainerCertificate.getIssuedBy())
                .issuedDate(trainerCertificate.getIssuedDate())
                .userId(trainerCertificate.getUser().getId())
                .build();

    }
}
