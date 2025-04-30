package com.gymforhealthy.gms.mapperImpl;

import com.gymforhealthy.gms.entity.Course;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.mapper.CourseMapper;
import com.gymforhealthy.gms.requestDto.CourseRequestDto;
import com.gymforhealthy.gms.responseDto.CourseResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public Course toEntity(CourseRequestDto courseRequestDto) {
    if (courseRequestDto == null)   return null;

    Course course = new Course();
    course.setName(courseRequestDto.getName());
    course.setMaxCapacity(courseRequestDto.getMaxCapacity());

    User trainer = new User();
    trainer.setId(courseRequestDto.getTrainerId());
    course.setTrainer(trainer);

    return course;

    }

    @Override
    public CourseResponseDto toResponseDto(Course course) {
    if (course == null)   return null;

    return CourseResponseDto.builder()
            .id(course.getId())
            .name(course.getName())
            .maxCapacity(course.getMaxCapacity())
            .trainerId(course.getTrainer().getId())
            .build();
    }
}
