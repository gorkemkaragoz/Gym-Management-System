package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.AnswerRequestDto;
import com.gymforhealthy.gms.dto.responseDto.AnswerResponseDto;

import java.util.List;

public interface AnswerService {
    AnswerResponseDto saveAnswer(AnswerRequestDto answerRequestDto);
    AnswerResponseDto findAnswerById(Long id);
    void deleteAnswer(Long id);
}
