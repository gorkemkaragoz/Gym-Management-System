package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.QuestionRequestDto;
import com.gymforhealthy.gms.dto.responseDto.QuestionResponseDto;

import java.util.List;

public interface QuestionService {
    QuestionResponseDto save(QuestionRequestDto questionRequestDto);
    QuestionResponseDto update(Integer id, QuestionRequestDto questionRequestDto);
    QuestionResponseDto findById(Integer id);
    List<QuestionResponseDto> findAll();
    List<QuestionResponseDto> findBySenderId(Integer senderId);
}
