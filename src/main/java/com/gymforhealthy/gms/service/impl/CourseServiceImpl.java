package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.CourseRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseResponseDto;
import com.gymforhealthy.gms.entity.Course;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.exception.ResourceNotFoundException;
import com.gymforhealthy.gms.repository.CourseRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.CourseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public CourseResponseDto saveCourse(CourseRequestDto courseRequestDto) {

        if(courseRequestDto.getMaxCapacity() > 25) {
            throw new IllegalArgumentException("Max capacity exceeded");
        }

        Course course = modelMapper.map(courseRequestDto, Course.class);
        course.setId(null);

        User trainer = userRepository.findById(courseRequestDto.getTrainerId())
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id" + courseRequestDto.getTrainerId()));

        if (!trainer.getRole().getName().equalsIgnoreCase("TRAINER")) {
            throw new IllegalArgumentException("Only users with TRAINER role can have course.");
        }

        course.setTrainer(trainer);

        course = courseRepository.save(course);
        return convertToResponseDto(course);
    }

    @Override
    public CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto) {

        if(courseRequestDto.getMaxCapacity() > 25) {
            throw new IllegalArgumentException("Max capacity exceeded");
        }

        Course course = courseRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Course not found with id" + id));

        // Var olan id'yi sakla
        Long originalId = course.getId();

        // DTO'dan gelen verileri Course nesnesine aktar
        modelMapper.map(courseRequestDto, course);

        // id'yi mapping sonrası geri yükle
        course.setId(originalId);

        User trainer = userRepository.findById(courseRequestDto.getTrainerId())
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id" + courseRequestDto.getTrainerId()));

        if (!trainer.getRole().getName().equalsIgnoreCase("TRAINER")) {
            throw new IllegalArgumentException("Only users with TRAINER role can have course.");
        }

        course.setTrainer(trainer);

        course = courseRepository.save(course);
        return convertToResponseDto(course);
    }

    @Override
    public List<CourseResponseDto> findAllCourse() {
        return courseRepository.findAll().stream()
                .map(course -> convertToResponseDto(course))
                .toList();
    }

    @Override
    public CourseResponseDto findCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id" +id));
        return convertToResponseDto(course);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id" +id));
        courseRepository.delete(course);
    }

    private CourseResponseDto convertToResponseDto(Course course) {
        CourseResponseDto courseResponseDto = modelMapper.map(course, CourseResponseDto.class);
        courseResponseDto.setTrainerId(course.getTrainer().getId());
        return courseResponseDto;
    }
}
