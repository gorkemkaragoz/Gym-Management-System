package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.CourseEnrollmentRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseEnrollmentResponseDto;
import com.gymforhealthy.gms.entity.Course;
import com.gymforhealthy.gms.entity.CourseEnrollment;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.repository.CourseEnrollmentRepository;
import com.gymforhealthy.gms.repository.CourseRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.CourseEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {

    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public void enrollUserToCourse(Integer userId, Integer courseId) {
        if (courseEnrollmentRepository.existsByUserIdAndCourseIdAndStatus(userId, courseId, CourseEnrollment.Status.ACTIVE)) {
            throw new RuntimeException("User already enrolled in this course.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        CourseEnrollment enrollment = new CourseEnrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setStatus(CourseEnrollment.Status.ACTIVE);

        courseEnrollmentRepository.save(enrollment);
    }

    @Override
    public void cancelEnrollmentByUserAndCourse(Integer userId, Integer courseId) {
        CourseEnrollment enrollment = courseEnrollmentRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setStatus(CourseEnrollment.Status.CANCELLED);
        courseEnrollmentRepository.save(enrollment);
    }

    @Override
    public boolean isUserEnrolled(Integer userId, Integer courseId) {
        return courseEnrollmentRepository.existsByUserIdAndCourseIdAndStatus(userId, courseId, CourseEnrollment.Status.ACTIVE);
    }
}
