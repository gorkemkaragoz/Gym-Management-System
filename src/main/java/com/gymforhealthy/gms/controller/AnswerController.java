package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.AnswerRequestDto;
import com.gymforhealthy.gms.dto.responseDto.AnswerResponseDto;
import com.gymforhealthy.gms.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<AnswerResponseDto> saveAnswer(@RequestBody AnswerRequestDto requestDto) {
        return ResponseEntity.ok(answerService.saveAnswer(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponseDto> findAnswerById(@PathVariable Long id) {
        return ResponseEntity.ok(answerService.findAnswerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}