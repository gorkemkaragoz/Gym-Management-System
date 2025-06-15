package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.BodyMeasurementRequestDto;
import com.gymforhealthy.gms.dto.responseDto.BodyMeasurementResponseDto;
import com.gymforhealthy.gms.entity.BodyMeasurement;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.exception.ResourceNotFoundException;
import com.gymforhealthy.gms.repository.BodyMeasurementRepository;
import com.gymforhealthy.gms.repository.CourseEnrollmentRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.BodyMeasurementService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@AllArgsConstructor
public class BodyMeasurementServiceImpl implements BodyMeasurementService {

    private final BodyMeasurementRepository bodyMeasurementRepository;
    private final UserRepository userRepository;
    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public BodyMeasurementResponseDto saveBodyMeasurement(BodyMeasurementRequestDto requestDto, Authentication authentication) throws AccessDeniedException {
        String trainerEmail = authentication.getName();
        User trainer = userRepository.findByEmail(trainerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with email: " + trainerEmail));

        User student = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestDto.getUserId()));

        if (!student.getRole().getName().equalsIgnoreCase("MEMBER")) {
            throw new IllegalArgumentException("Only users with MEMBER role can have measurement.");
        }

        boolean isStudentOfTrainer = courseEnrollmentRepository.existsByTrainerAndStudent(trainer.getId(), student.getId());
        if (!isStudentOfTrainer) {
            throw new AccessDeniedException("You are not allowed to add measurement for this user.");
        }

        BodyMeasurement bodyMeasurement = modelMapper.map(requestDto, BodyMeasurement.class);
        bodyMeasurement.setId(null);
        bodyMeasurement.setUser(student);

        calculateAndSetBmi(bodyMeasurement);

        bodyMeasurement = bodyMeasurementRepository.save(bodyMeasurement);
        return convertToResponseDto(bodyMeasurement);
    }

    @Override
    public BodyMeasurementResponseDto updateBodyMeasurement(Long id, BodyMeasurementRequestDto bodyMeasurementRequestDto) {
        // Mevcut kaydı bul, yoksa hata fırlat
        BodyMeasurement bodyMeasurement = bodyMeasurementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BodyMeasurement not found with id: " + id));

        // DTO'dan gelen verileri mevcut entity'ye kopyala
        modelMapper.map(bodyMeasurementRequestDto, bodyMeasurement);

        // Kullanıcıyı bul, yoksa hata fırlat
        User user = userRepository.findById(bodyMeasurementRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + bodyMeasurementRequestDto.getUserId()));

        if (!user.getRole().getName().equalsIgnoreCase("MEMBER")) {
            throw new IllegalArgumentException("Only users with MEMBER role can have measurement.");
        }

        bodyMeasurement.setUser(user);

        // BMI hesapla ve entity'ye set et
        calculateAndSetBmi(bodyMeasurement);

        // Güncellenmiş entity'yi kaydet ve DTO'ya dönüştür
        bodyMeasurement = bodyMeasurementRepository.save(bodyMeasurement);
        return convertToResponseDto(bodyMeasurement);
    }

    @Override
    public BodyMeasurementResponseDto findLastMeasurementByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        BodyMeasurement latest = bodyMeasurementRepository
                .findTopByUserOrderByCreatedTimeDesc(user)
                .orElseThrow(() -> new ResourceNotFoundException("No body measurement found for user"));

        return convertToResponseDto(latest);
    }

    @Override
    public List<BodyMeasurementResponseDto> findAllBodyMeasurement() {
        // Tüm ölçümleri bul ve DTO listesine dönüştür
        return bodyMeasurementRepository.findAll().stream()
                .map(bodyMeasurement -> convertToResponseDto(bodyMeasurement))
                .toList();
    }

    @Override
    public BodyMeasurementResponseDto findBodyMeasurementById(Long id) {
        // ID ile ölçümü bul, yoksa hata fırlat
        BodyMeasurement bodyMeasurement = bodyMeasurementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BodyMeasurement not found with id: " + id));

        // Entity'yi DTO'ya dönüştür
        return convertToResponseDto(bodyMeasurement);
    }

    @Override
    public void deleteBodyMeasurement(Long id) {
        // ID ile ölçümü bul, yoksa hata fırlat
        BodyMeasurement bodyMeasurement = bodyMeasurementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BodyMeasurement not found with id: " + id));

        // Ölçümü sil
        bodyMeasurementRepository.delete(bodyMeasurement);
    }

    private void calculateAndSetBmi(BodyMeasurement bodyMeasurement) {
        // BMI hesapla: weight / (height * height) - height'ı metreye çevir (santimetre -> metre)
        if (bodyMeasurement.getHeight() != null && bodyMeasurement.getWeight() != null
                && bodyMeasurement.getHeight().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal heightInCentimeters = bodyMeasurement.getHeight();
            BigDecimal heightInMeters = heightInCentimeters.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            BigDecimal weightInKg = bodyMeasurement.getWeight();
            BigDecimal bmi = weightInKg.divide(heightInMeters.multiply(heightInMeters), 2, RoundingMode.HALF_UP);
            bodyMeasurement.setBmi(bmi); // BMI'yi entity'ye set et
        } else {
            bodyMeasurement.setBmi(BigDecimal.ZERO); // Geçersiz verilerde BMI 0 olarak set edilir
        }
    }

    private BodyMeasurementResponseDto convertToResponseDto(BodyMeasurement bodyMeasurement) {
        // Entity'yi DTO'ya dönüştür
        BodyMeasurementResponseDto bodyMeasurementResponseDto = modelMapper.map(bodyMeasurement, BodyMeasurementResponseDto.class);
        bodyMeasurementResponseDto.setUserId(bodyMeasurement.getUser().getId());
        // BMI zaten entity'de hesaplanıp set edildiği için tekrar hesaplamaya gerek yok
        return bodyMeasurementResponseDto;
    }
}