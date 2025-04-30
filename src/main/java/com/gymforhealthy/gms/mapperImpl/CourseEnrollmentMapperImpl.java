package com.gymforhealthy.gms.mapperImpl;

import com.gymforhealthy.gms.entity.Course;
import com.gymforhealthy.gms.entity.CourseEnrollment;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.mapper.CourseEnrollmentMapper;
import com.gymforhealthy.gms.requestDto.CourseEnrollmentRequestDto;
import com.gymforhealthy.gms.responseDto.CourseEnrollmentResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CourseEnrollmentMapperImpl implements CourseEnrollmentMapper {

    @Override
    public CourseEnrollment toEntity(CourseEnrollmentRequestDto courseEnrollmentRequestDto) {
    if (courseEnrollmentRequestDto == null)   return null;

    CourseEnrollment courseEnrollment = new CourseEnrollment();
    courseEnrollment.setStatus(CourseEnrollment.Status.ACTIVE); // varsayılan değer

    User user = new User();
    user.setId(courseEnrollmentRequestDto.getUserId());
    courseEnrollment.setUser(user);

    Course course = new Course();
    course.setId(courseEnrollmentRequestDto.getCourseId());
    courseEnrollment.setCourse(course);

    return  courseEnrollment;

    }

    @Override
    public CourseEnrollmentResponseDto toResponseDto(CourseEnrollment courseEnrollment) {
    if (courseEnrollment == null)   return null;

    return CourseEnrollmentResponseDto.builder()
            .id(courseEnrollment.getId())
            .userId(courseEnrollment.getUser().getId())
            .courseId(courseEnrollment.getCourse().getId())
            .status(courseEnrollment.getStatus().name())
            .build();
    }


}
