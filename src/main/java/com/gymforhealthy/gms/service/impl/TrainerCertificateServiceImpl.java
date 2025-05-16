package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.TrainerCertificateRequestDto;
import com.gymforhealthy.gms.dto.responseDto.TrainerCertificateResponseDto;
import com.gymforhealthy.gms.entity.TrainerCertificate;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.exception.ResourceNotFoundException;
import com.gymforhealthy.gms.repository.TrainerCertificateRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.TrainerCertificateService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerCertificateServiceImpl implements TrainerCertificateService {

    private final TrainerCertificateRepository trainerCertificateRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public TrainerCertificateResponseDto saveTrainerCertificate(TrainerCertificateRequestDto trainerCertificateRequestDto) {

        TrainerCertificate trainerCertificate = modelMapper.map(trainerCertificateRequestDto, TrainerCertificate.class);
        trainerCertificate.setId(null);

        User user = userRepository.findById(trainerCertificateRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + trainerCertificateRequestDto.getUserId()));

        if (!user.getRole().getName().equalsIgnoreCase("TRAINER")) {
            throw new IllegalArgumentException("Only users with TRAINER role can have certificates.");
        }

        trainerCertificate.setUser(user);

        trainerCertificate = trainerCertificateRepository.save(trainerCertificate);
        return convertToResponseDto(trainerCertificate);
    }


    @Override
    public TrainerCertificateResponseDto updateTrainerCertificate(Long id, TrainerCertificateRequestDto trainerCertificateRequestDto) {

        TrainerCertificate trainerCertificate = trainerCertificateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer Certificate not found with id: " + id));

        modelMapper.map(trainerCertificateRequestDto, trainerCertificate);

        User user = userRepository.findById(trainerCertificateRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + trainerCertificateRequestDto.getUserId()));

        if (!user.getRole().getName().equalsIgnoreCase("TRAINER")) {
            throw new IllegalArgumentException("Only users with TRAINER role can have certificates.");
        }

        trainerCertificate.setUser(user);

        trainerCertificate = trainerCertificateRepository.save(trainerCertificate);
        return convertToResponseDto(trainerCertificate);
    }


    @Override
    public List<TrainerCertificateResponseDto> findTrainerCertificatesByUserId(Long userId) {
        return trainerCertificateRepository.findAll().stream()
                .filter(trainerCertificate -> trainerCertificate.getUser().getId().equals(userId))
                .map(trainerCertificate -> convertToResponseDto(trainerCertificate))
                .toList();
    }

    @Override
    public TrainerCertificateResponseDto findTrainerCertificateById(Long id) {
        TrainerCertificate trainerCertificate = trainerCertificateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer Certificate not found with id: " + id));
        return convertToResponseDto(trainerCertificate);
    }

    @Override
    public void deleteTrainerCertificate(Long id) {
        TrainerCertificate trainerCertificate = trainerCertificateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer Certificate not found with id: " + id));
        trainerCertificateRepository.delete(trainerCertificate);
    }

    private TrainerCertificateResponseDto convertToResponseDto(TrainerCertificate trainerCertificate) {
        TrainerCertificateResponseDto trainerCertificateResponseDto = modelMapper.map(trainerCertificate, TrainerCertificateResponseDto.class);
        trainerCertificateResponseDto.setUserId(trainerCertificate.getUser().getId());
        return trainerCertificateResponseDto;
    }
}