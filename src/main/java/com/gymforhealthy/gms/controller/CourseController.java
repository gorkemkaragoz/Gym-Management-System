package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.CourseRequestDto;
import com.gymforhealthy.gms.dto.responseDto.CourseResponseDto;
import com.gymforhealthy.gms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponseDto> save(@RequestBody CourseRequestDto courseRequestDto) {
        return ResponseEntity.ok(courseService.save(courseRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> update(@PathVariable Integer id,
                                                    @RequestBody CourseRequestDto courseRequestDto) {
        return ResponseEntity.ok(courseService.update(id, courseRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }
}
