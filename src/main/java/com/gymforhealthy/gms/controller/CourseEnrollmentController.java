package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.CourseEnrollmentRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseEnrollmentResponseDto;
import com.gymforhealthy.gms.service.CourseEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-enrollments")
@RequiredArgsConstructor
public class CourseEnrollmentController {

    private final CourseEnrollmentService courseEnrollmentService;

    @PostMapping
    public ResponseEntity<CourseEnrollmentResponseDto> saveCourseEnrollment(
            @RequestBody CourseEnrollmentRequestDto requestDto) {
        return ResponseEntity.ok(courseEnrollmentService.saveCourseEnrollment(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseEnrollmentResponseDto> updateCourseEnrollment(
            @PathVariable Long id,
            @RequestBody CourseEnrollmentRequestDto requestDto) {
        return ResponseEntity.ok(courseEnrollmentService.updateCourseEnrollment(id, requestDto));
    }

    @GetMapping
    public ResponseEntity<List<CourseEnrollmentResponseDto>> findAllCourseEnrollment() {
        return ResponseEntity.ok(courseEnrollmentService.findAllCourseEnrollment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEnrollmentResponseDto> findCourseEnrollmentById(@PathVariable Long id) {
        return ResponseEntity.ok(courseEnrollmentService.findCourseEnrollmentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseEnrollment(@PathVariable Long id) {
        courseEnrollmentService.deleteCourseEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}