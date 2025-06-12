package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.CourseEnrollmentRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseEnrollmentResponseDto;
import com.gymforhealthy.gms.service.CourseEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j; // en üste ekle

import java.util.List;

@Slf4j // class üstüne ekle
@RestController
@RequestMapping("/api/v1/course-enrollments")
@RequiredArgsConstructor
public class CourseEnrollmentController {

    private final CourseEnrollmentService courseEnrollmentService;

    /**
     * Üye ders programına katılır (JOIN)
     */
    @PostMapping("/enroll")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<CourseEnrollmentResponseDto> enrollCourse(@RequestBody CourseEnrollmentRequestDto requestDto) {
        log.info("Enroll endpoint called. scheduleId: {}", requestDto.getCourseScheduleId());
        CourseEnrollmentResponseDto response = courseEnrollmentService.saveCourseEnrollment(requestDto);
        log.info("Enrollment created. Response: {}", response);
        return ResponseEntity.ok(response);
    }

    /**
     * Üye dersi iptal eder (CANCEL)
     */
    @DeleteMapping("/cancel/{scheduleId}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<String> cancelEnrollment(@PathVariable Long scheduleId) {
        log.info("Cancel enrollment called. scheduleId: {}", scheduleId);
        courseEnrollmentService.cancelEnrollment(scheduleId);
        log.info("Enrollment cancelled.");
        return ResponseEntity.ok("Enrollment cancelled successfully");
    }

    // Eğer admin tarafında enrollment silme işlemi olacaksa (enrollmentId üzerinden):
    @DeleteMapping("/{enrollmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEnrollmentByAdmin(@PathVariable Long enrollmentId) {
        courseEnrollmentService.deleteCourseEnrollment(enrollmentId);
        return ResponseEntity.ok("Enrollment deleted by admin");
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<List<CourseEnrollmentResponseDto>> getMyEnrollments() {
        return ResponseEntity.ok(courseEnrollmentService.getMyEnrollments());
    }

}