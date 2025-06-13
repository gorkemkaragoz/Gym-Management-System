package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.CourseEnrollmentRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseEnrollmentResponseDto;
import com.gymforhealthy.gms.entity.CourseEnrollment;
import com.gymforhealthy.gms.entity.CourseSchedule;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.exception.ResourceNotFoundException;
import com.gymforhealthy.gms.repository.CourseEnrollmentRepository;
import com.gymforhealthy.gms.repository.CourseScheduleRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.CourseEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {

    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final UserRepository userRepository;
    private final CourseScheduleRepository courseScheduleRepository;
    private final ModelMapper modelMapper;

    @Override
    public CourseEnrollmentResponseDto saveCourseEnrollment(CourseEnrollmentRequestDto requestDto) {
        Long userId = getCurrentUserIdFromSecurity();

        CourseSchedule schedule = courseScheduleRepository.findById(requestDto.getCourseScheduleId())
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id: " + requestDto.getCourseScheduleId()));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Kurs kapasite kontrolü
        long enrolledCount = courseEnrollmentRepository.countByCourseScheduleId(schedule.getId());
        if (enrolledCount >= schedule.getCourse().getMaxCapacity()) {
            throw new IllegalArgumentException("Course is already full.");
        }

        // Aynı kursa zaten kayıtlı mı?
        if (courseEnrollmentRepository.existsByUserIdAndCourseScheduleId(userId, schedule.getId())) {
            throw new IllegalStateException("Already enrolled to this course.");
        }

        // Tarih ve saat çakışması var mı? (Kendi başka dersiyle)
        boolean hasTimeConflict = courseEnrollmentRepository.existsUserScheduleConflict(
                userId,
                schedule.getCourseDate(),
                schedule.getStartTime(),
                schedule.getEndTime()
        );
        if (hasTimeConflict) {
            throw new IllegalStateException("You already have another course at that time.");
        }

        // Kayıt oluşturuluyor
        CourseEnrollment enrollment = new CourseEnrollment();
        enrollment.setUser(user);
        enrollment.setCourseSchedule(schedule);
        enrollment.setCourse(schedule.getCourse());
        enrollment.setStatus(CourseEnrollment.Status.ACTIVE);

        CourseEnrollment saved = courseEnrollmentRepository.save(enrollment);
        return convertToCourseEnrollmentResponseDto(saved);
    }


    @Override
    public void deleteCourseEnrollment(Long enrollmentId) {
        CourseEnrollment enrollment = courseEnrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));
        courseEnrollmentRepository.delete(enrollment);
    }

    @Override
    public void cancelEnrollment(Long scheduleId) {
        Long userId = getCurrentUserIdFromSecurity();

        if (!courseEnrollmentRepository.existsByUserIdAndCourseScheduleId(userId, scheduleId)) {
            throw new IllegalStateException("No enrollment found to cancel.");
        }

        courseEnrollmentRepository.deleteByUserIdAndCourseScheduleId(userId, scheduleId);
    }

    @Override
    public List<CourseEnrollmentResponseDto> getMyEnrollments() {
        Long userId = getCurrentUserIdFromSecurity();

        List<CourseEnrollment> enrollments = courseEnrollmentRepository.findByUserId(userId);

        return enrollments.stream().map(enrollment -> {
            CourseSchedule schedule = enrollment.getCourseSchedule();

            CourseEnrollmentResponseDto dto = new CourseEnrollmentResponseDto();
            dto.setScheduleId(schedule.getId());
            dto.setCourseName(schedule.getCourse().getName());
            dto.setCourseDate(schedule.getCourseDate().toString());
            dto.setStartTime(schedule.getStartTime().toString());
            dto.setEndTime(schedule.getEndTime().toString());
            return dto;
        }).collect(Collectors.toList());
    }

    private CourseEnrollmentResponseDto convertToCourseEnrollmentResponseDto(CourseEnrollment enrollment) {
        CourseEnrollmentResponseDto dto = new CourseEnrollmentResponseDto();
        dto.setId(enrollment.getId());
        dto.setUserId(enrollment.getUser().getId());
        dto.setCourseId(enrollment.getCourseSchedule().getCourse().getId());
        dto.setStatus(enrollment.getStatus().name());
        return dto;
    }

    private Long getCurrentUserIdFromSecurity() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return user.getId();
    }
}