package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.CourseScheduleRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseScheduleOverviewResponseDto;
import com.gymforhealthy.gms.dto.responseDto.CourseScheduleResponseDto;
import com.gymforhealthy.gms.dto.responseDto.EnrolledStudentDto;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface CourseScheduleService {
    CourseScheduleResponseDto saveCourseSchedule(CourseScheduleRequestDto courseScheduleRequestDto);
    CourseScheduleResponseDto updateCourseSchedule(Long id, CourseScheduleRequestDto courseScheduleRequestDto);
    List<CourseScheduleResponseDto> findAllCourseSchedule();
    CourseScheduleResponseDto findCourseScheduleById(Long id);
    void deleteCourseSchedule(Long id);
    List<CourseScheduleOverviewResponseDto> findAllSchedulesOverview();
    List<CourseScheduleOverviewResponseDto> getScheduleForTrainer(String email);
    List<EnrolledStudentDto> getAllStudentsForTrainer(String trainerEmail);
}
