package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.CourseScheduleRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseScheduleOverviewResponseDto;
import com.gymforhealthy.gms.dto.responseDto.CourseScheduleResponseDto;
import com.gymforhealthy.gms.dto.responseDto.EnrolledStudentDto;
import com.gymforhealthy.gms.service.CourseScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j; // LOG için ekle

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/course-schedules")
@RequiredArgsConstructor
public class CourseScheduleController {

    private final CourseScheduleService courseScheduleService;

    @PostMapping
    public ResponseEntity<CourseScheduleResponseDto> createCourseSchedule(
            @RequestBody CourseScheduleRequestDto courseScheduleRequestDto) {
        CourseScheduleResponseDto savedSchedule = courseScheduleService.saveCourseSchedule(courseScheduleRequestDto);
        return ResponseEntity.ok(savedSchedule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseScheduleResponseDto> updateCourseSchedule(
            @PathVariable Long id,
            @RequestBody CourseScheduleRequestDto courseScheduleRequestDto) {
        CourseScheduleResponseDto updatedSchedule = courseScheduleService.updateCourseSchedule(id, courseScheduleRequestDto);
        return ResponseEntity.ok(updatedSchedule);
    }

    @GetMapping
    public ResponseEntity<List<CourseScheduleResponseDto>> getAllCourseSchedules() {
        return ResponseEntity.ok(courseScheduleService.findAllCourseSchedule());
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<List<CourseScheduleOverviewResponseDto>> getMyLessons(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(courseScheduleService.getScheduleForTrainer(email));
    }

    @GetMapping("/my-students")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<List<EnrolledStudentDto>> getAllMyStudents(Authentication authentication) {
        String trainerEmail = authentication.getName();
        List<EnrolledStudentDto> students = courseScheduleService.getAllStudentsForTrainer(trainerEmail);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/overview")
    public ResponseEntity<List<CourseScheduleOverviewResponseDto>> getSchedulesOverview() {
        List<CourseScheduleOverviewResponseDto> list = courseScheduleService.findAllSchedulesOverview();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseScheduleResponseDto> getCourseScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(courseScheduleService.findCourseScheduleById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseSchedule(@PathVariable Long id) {
        courseScheduleService.deleteCourseSchedule(id);
        return ResponseEntity.noContent().build();
    }
}