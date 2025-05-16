package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.QuestionRequestDto;
import com.gymforhealthy.gms.dto.responseDto.QuestionResponseDto;
import com.gymforhealthy.gms.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    @Operation(summary = "Save a new question")
    public ResponseEntity<QuestionResponseDto> saveQuestion(@RequestBody QuestionRequestDto requestDto) {
        QuestionResponseDto responseDto = questionService.saveQuestion(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find question by ID")
    public ResponseEntity<QuestionResponseDto> findQuestionById(@PathVariable Long id) {
        QuestionResponseDto responseDto = questionService.findQuestionById(id);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete question by ID")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

}