package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.CourseRequestDto;
import com.gymforhealthy.gms.dto.requestDto.CourseScheduleRequestDto;
import com.gymforhealthy.gms.dto.requestDto.CourseWithScheduleRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseResponseDto;
import com.gymforhealthy.gms.dto.responseDto.CourseScheduleOverviewResponseDto;
import com.gymforhealthy.gms.service.CourseScheduleService;
import com.gymforhealthy.gms.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseScheduleService courseScheduleService;

    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(@RequestBody CourseRequestDto courseRequestDto) {
        return ResponseEntity.ok(courseService.saveCourse(courseRequestDto));
    }

    @PostMapping("with-schedule")
    @Transactional
    public ResponseEntity<Void> createCourseWithSchedule(
            @RequestBody CourseWithScheduleRequestDto dto
    ) {
        // 1) Kursu kaydet
        CourseRequestDto courseDto = CourseRequestDto.builder()
                .name(dto.getName())
                .maxCapacity(dto.getMaxCapacity())
                .trainerId(dto.getTrainerId())
                .build();
        CourseResponseDto savedCourse = courseService.saveCourse(courseDto);

        // 2) O kursun ID’si ile ders programını kaydet
        CourseScheduleRequestDto schedDto = CourseScheduleRequestDto.builder()
                .courseId(savedCourse.getId())
                .trainerId(dto.getTrainerId())
                .courseDate(dto.getCourseDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .build();
        courseScheduleService.saveCourseSchedule(schedDto);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDto courseRequestDto) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAllCourse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findCourseById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }


}
