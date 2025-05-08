package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.TrainerCertificateRequestDto;
import com.gymforhealthy.gms.dto.responseDto.TrainerCertificateResponseDto;
import com.gymforhealthy.gms.entity.TrainerCertificate;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.repository.TrainerCertificateRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.TrainerCertificateService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainerCertificateServiceImpl implements TrainerCertificateService {

    private final TrainerCertificateRepository trainerCertificateRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public TrainerCertificateResponseDto save(TrainerCertificateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User (trainer) not found"));

        TrainerCertificate certificate = modelMapper.map(requestDto, TrainerCertificate.class);
        certificate.setUser(user);

        TrainerCertificate saved = trainerCertificateRepository.save(certificate);
        return modelMapper.map(saved, TrainerCertificateResponseDto.class);
    }

    @Override
    public List<TrainerCertificateResponseDto> findByTrainerId(Integer trainerId) {
        return trainerCertificateRepository.findByUserId(trainerId)
                .stream()
                .map(cert -> modelMapper.map(cert, TrainerCertificateResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        trainerCertificateRepository.deleteById(id);
    }

    @Override
    public List<TrainerCertificateResponseDto> findByUserId(Integer userId) {
        return trainerCertificateRepository.findByUserId(userId)
                .stream()
                .map(c -> modelMapper.map(c, TrainerCertificateResponseDto.class))
                .collect(Collectors.toList());
    }
}
