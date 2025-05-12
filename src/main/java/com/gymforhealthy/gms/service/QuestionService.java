package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.QuestionRequestDto;
import com.gymforhealthy.gms.dto.responseDto.QuestionResponseDto;

import java.util.List;

public interface QuestionService {
    QuestionResponseDto saveQuestion(QuestionRequestDto questionRequestDto);
    QuestionResponseDto updateQuestion(Long id, QuestionRequestDto questionRequestDto);
    List<QuestionResponseDto> findAllQuestions();
    QuestionResponseDto findQuestionById(Long id);
    void deleteQuestion(Long id);
}
