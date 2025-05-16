//package com.gymforhealthy.gms.service.impl;
//
//import com.gymforhealthy.gms.dto.requestDto.CourseEnrollmentRequestDto;
//import com.gymforhealthy.gms.dto.responseDto.CourseEnrollmentResponseDto;
//import com.gymforhealthy.gms.entity.Course;
//import com.gymforhealthy.gms.entity.CourseEnrollment;
//import com.gymforhealthy.gms.entity.User;
//import com.gymforhealthy.gms.exception.ResourceNotFoundException;
//import com.gymforhealthy.gms.repository.CourseEnrollmentRepository;
//import com.gymforhealthy.gms.repository.CourseRepository;
//import com.gymforhealthy.gms.repository.UserRepository;
//import com.gymforhealthy.gms.service.CourseEnrollmentService;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {
//
//    private final CourseEnrollmentRepository courseEnrollmentRepository;
//    private final UserRepository userRepository;
//    private final CourseRepository courseRepository;
//    private final ModelMapper modelMapper;
//
//    @Override
//    public CourseEnrollmentResponseDto saveCourseEnrollment(CourseEnrollmentRequestDto requestDto) {
//        User user = userRepository.findById(requestDto.getUserId())
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestDto.getUserId()));
//
//        Course course = courseRepository.findById(requestDto.getCourseId())
//                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + requestDto.getCourseId()));
//
//        CourseEnrollment enrollment = new CourseEnrollment();
//        enrollment.setUser(user);
//        enrollment.setCourse(course);
//
//        CourseEnrollment saved = courseEnrollmentRepository.save(enrollment);
//        return convertToCourseEnrollmentResponseDto(saved);
//    }
//
//    @Override
//    public CourseEnrollmentResponseDto updateCourseEnrollment(Long id, CourseEnrollmentRequestDto requestDto) {
//        CourseEnrollment enrollment = courseEnrollmentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("CourseEnrollment not found with id: " + id));
//
//        User user = userRepository.findById(requestDto.getUserId())
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestDto.getUserId()));
//
//        Course course = courseRepository.findById(requestDto.getCourseId())
//                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + requestDto.getCourseId()));
//
//        enrollment.setUser(user);
//        enrollment.setCourse(course);
//
//        CourseEnrollment updated = courseEnrollmentRepository.save(enrollment);
//        return convertToCourseEnrollmentResponseDto(updated);
//    }
//
//    @Override
//    public List<CourseEnrollmentResponseDto> findAllCourseEnrollment() {
//        return courseEnrollmentRepository.findAll().stream()
//                .map(this::convertToCourseEnrollmentResponseDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public CourseEnrollmentResponseDto findCourseEnrollmentById(Long id) {
//        CourseEnrollment enrollment = courseEnrollmentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("CourseEnrollment not found with id: " + id));
//        return convertToCourseEnrollmentResponseDto(enrollment);
//    }
//
//    @Override
//    public void deleteCourseEnrollment(Long id) {
//        CourseEnrollment enrollment = courseEnrollmentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("CourseEnrollment not found with id: " + id));
//        courseEnrollmentRepository.delete(enrollment);
//    }
//
//    private CourseEnrollmentResponseDto convertToCourseEnrollmentResponseDto(CourseEnrollment enrollment) {
//        CourseEnrollmentResponseDto dto = modelMapper.map(enrollment, CourseEnrollmentResponseDto.class);
//        dto.setUserId(enrollment.getUser().getId());
//        dto.setCourseId(enrollment.getCourse().getId());
//        return dto;
//    }
//}

package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.CourseEnrollmentRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseEnrollmentResponseDto;
import com.gymforhealthy.gms.entity.Course;
import com.gymforhealthy.gms.entity.CourseEnrollment;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.exception.ResourceNotFoundException;
import com.gymforhealthy.gms.repository.CourseEnrollmentRepository;
import com.gymforhealthy.gms.repository.CourseRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.CourseEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {

    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public CourseEnrollmentResponseDto saveCourseEnrollment(CourseEnrollmentRequestDto requestDto) {
        Course course = courseRepository.findById(requestDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + requestDto.getCourseId()));

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestDto.getUserId()));

        long enrolledCount = courseEnrollmentRepository.countByCourseId(course.getId());
        if (enrolledCount >= course.getMaxCapacity()) {
            throw new IllegalArgumentException("Course is already at full capacity.");
        }

        CourseEnrollment enrollment = new CourseEnrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);

        enrollment = courseEnrollmentRepository.save(enrollment);
        return convertToCourseEnrollmentResponseDto(enrollment);
    }

    @Override
    public CourseEnrollmentResponseDto updateCourseEnrollment(Long id, CourseEnrollmentRequestDto requestDto) {
        CourseEnrollment enrollment = courseEnrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));

        Course course = courseRepository.findById(requestDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + requestDto.getCourseId()));

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestDto.getUserId()));

        long enrolledCount = courseEnrollmentRepository.countByCourseId(course.getId());
        if (enrolledCount >= course.getMaxCapacity() && !enrollment.getCourse().getId().equals(course.getId())) {
            throw new IllegalArgumentException("Course is already at full capacity.");
        }

        enrollment.setCourse(course);
        enrollment.setUser(user);

        enrollment = courseEnrollmentRepository.save(enrollment);
        return convertToCourseEnrollmentResponseDto(enrollment);
    }

    @Override
    public List<CourseEnrollmentResponseDto> findAllCourseEnrollment() {
        return courseEnrollmentRepository.findAll().stream()
                .map(this::convertToCourseEnrollmentResponseDto)
                .toList();
    }

    @Override
    public CourseEnrollmentResponseDto findCourseEnrollmentById(Long id) {
        CourseEnrollment enrollment = courseEnrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
        return convertToCourseEnrollmentResponseDto(enrollment);
    }

    @Override
    public void deleteCourseEnrollment(Long id) {
        CourseEnrollment enrollment = courseEnrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
        courseEnrollmentRepository.delete(enrollment);
    }

    private CourseEnrollmentResponseDto convertToCourseEnrollmentResponseDto(CourseEnrollment enrollment) {
        CourseEnrollmentResponseDto courseEnrollmentResponseDto = new CourseEnrollmentResponseDto();
        courseEnrollmentResponseDto.setId(enrollment.getId());
        courseEnrollmentResponseDto.setUserId(enrollment.getUser().getId());
        courseEnrollmentResponseDto.setCourseId(enrollment.getCourse().getId());
        courseEnrollmentResponseDto.setStatus(enrollment.getStatus().name());
        return courseEnrollmentResponseDto;
    }
}
