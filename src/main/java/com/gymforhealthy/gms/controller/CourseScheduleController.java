package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.CourseScheduleRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseScheduleOverviewResponseDto;
import com.gymforhealthy.gms.dto.responseDto.CourseScheduleResponseDto;
import com.gymforhealthy.gms.service.CourseScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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