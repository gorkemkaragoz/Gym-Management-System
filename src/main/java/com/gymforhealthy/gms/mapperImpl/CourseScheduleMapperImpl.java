package com.gymforhealthy.gms.mapperImpl;

import com.gymforhealthy.gms.entity.Course;
import com.gymforhealthy.gms.entity.CourseSchedule;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.mapper.CourseScheduleMapper;
import com.gymforhealthy.gms.requestDto.CourseScheduleRequestDto;
import com.gymforhealthy.gms.responseDto.CourseScheduleResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CourseScheduleMapperImpl implements CourseScheduleMapper {


    @Override
    public CourseSchedule toEntity(CourseScheduleRequestDto courseScheduleRequestDto) {
    if (courseScheduleRequestDto == null)   return null;

    CourseSchedule courseSchedule = new CourseSchedule();
    courseSchedule.setCourseDate(courseScheduleRequestDto.getCourseDate());
    courseSchedule.setStartTime(courseScheduleRequestDto.getStartTime());
    courseSchedule.setEndTime(courseScheduleRequestDto.getEndTime());

        Course course = new Course();
        course.setId(courseScheduleRequestDto.getCourseId());
        courseSchedule.setCourse(course);

        User trainer = new User();
        trainer.setId(courseScheduleRequestDto.getTrainerId());
        courseSchedule.setTrainer(trainer);

        return courseSchedule;

    }

    @Override
    public CourseScheduleResponseDto toResponseDto(CourseSchedule courseSchedule) {
    if (courseSchedule == null)   return null;

    return CourseScheduleResponseDto.builder()
            .id(courseSchedule.getId())
            .courseDate(courseSchedule.getCourseDate())
            .startTime(courseSchedule.getStartTime())
            .endTime(courseSchedule.getEndTime())
            .courseId(courseSchedule.getCourse().getId())
            .trainerId(courseSchedule.getTrainer().getId())
            .build();
    }
}
