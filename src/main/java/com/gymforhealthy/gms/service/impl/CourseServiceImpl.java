package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.CourseRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseResponseDto;
import com.gymforhealthy.gms.entity.Course;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.repository.CourseRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public CourseResponseDto save(CourseRequestDto requestDto) {
        User trainer = userRepository.findById(requestDto.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        Course course = modelMapper.map(requestDto, Course.class);
        course.setTrainer(trainer);

        Course saved = courseRepository.save(course);
        return modelMapper.map(saved, CourseResponseDto.class);
    }

    @Override
    public CourseResponseDto update(Integer id, CourseRequestDto requestDto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        modelMapper.map(requestDto, course); // mevcut course nesnesi üzerine map
        User trainer = userRepository.findById(requestDto.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        course.setTrainer(trainer);

        return modelMapper.map(courseRepository.save(course), CourseResponseDto.class);
    }

    @Override
    public void delete(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseResponseDto findById(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return modelMapper.map(course, CourseResponseDto.class);
    }

    @Override
    public List<CourseResponseDto> findAll() {
        return courseRepository.findAll()
                .stream()
                .map(course -> modelMapper.map(course, CourseResponseDto.class))
                .collect(Collectors.toList());
    }
}
