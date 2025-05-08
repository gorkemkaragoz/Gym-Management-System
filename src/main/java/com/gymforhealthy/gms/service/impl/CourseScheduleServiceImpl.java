package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.CourseScheduleRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseScheduleResponseDto;
import com.gymforhealthy.gms.entity.Course;
import com.gymforhealthy.gms.entity.CourseSchedule;
import com.gymforhealthy.gms.repository.CourseRepository;
import com.gymforhealthy.gms.repository.CourseScheduleRepository;
import com.gymforhealthy.gms.service.CourseScheduleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseScheduleServiceImpl implements CourseScheduleService {

    private final CourseScheduleRepository courseScheduleRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public CourseScheduleResponseDto save(CourseScheduleRequestDto requestDto) {
        Course course = courseRepository.findById(requestDto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        CourseSchedule schedule = modelMapper.map(requestDto, CourseSchedule.class);
        schedule.setCourse(course);

        CourseSchedule saved = courseScheduleRepository.save(schedule);
        return modelMapper.map(saved, CourseScheduleResponseDto.class);
    }

    @Override
    public CourseScheduleResponseDto update(Integer id, CourseScheduleRequestDto requestDto) {
        CourseSchedule schedule = courseScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        Course course = courseRepository.findById(requestDto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        modelMapper.map(requestDto, schedule);
        schedule.setCourse(course);

        return modelMapper.map(courseScheduleRepository.save(schedule), CourseScheduleResponseDto.class);
    }

    @Override
    public void delete(Integer id) {
        courseScheduleRepository.deleteById(id);
    }

    @Override
    public List<CourseScheduleResponseDto> findAll() {
        return courseScheduleRepository.findAll()
                .stream()
                .map(schedule -> modelMapper.map(schedule, CourseScheduleResponseDto.class))
                .collect(Collectors.toList());
    }
}
