package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.service.CourseEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class CourseEnrollmentController {

    private final CourseEnrollmentService courseEnrollmentService;

    // Kullanıcı derse katılır (JOIN)
    @PostMapping("/join")
    public ResponseEntity<String> joinCourse(@RequestParam Integer userId, @RequestParam Integer courseId) {
        courseEnrollmentService.enrollUserToCourse(userId, courseId);
        return ResponseEntity.ok("User successfully enrolled to the course.");
    }

    // Kullanıcı dersten çıkar (CANCEL)
    @PostMapping("/cancel")
    public ResponseEntity<String> cancelEnrollment(@RequestParam Integer userId, @RequestParam Integer courseId) {
        courseEnrollmentService.cancelEnrollmentByUserAndCourse(userId, courseId);
        return ResponseEntity.ok("Enrollment successfully cancelled.");
    }

    // GET http://localhost:8080/api/v1/enrollments/is-enrolled?userId=3&courseId=1
    // Kullanıcı bu derse kayıtlı mı? (Buton kontrolü için)
    @GetMapping("/is-enrolled")
    public ResponseEntity<Boolean> isEnrolled(@RequestParam Integer userId, @RequestParam Integer courseId) {
        boolean enrolled = courseEnrollmentService.isUserEnrolled(userId, courseId);
        return ResponseEntity.ok(enrolled);
    }
}
