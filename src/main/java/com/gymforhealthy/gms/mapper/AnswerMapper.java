package com.gymforhealthy.gms.mapper;

import com.gymforhealthy.gms.entity.Answer;
import com.gymforhealthy.gms.requestDto.AnswerRequestDto;
import com.gymforhealthy.gms.responseDto.AnswerResponseDto;

public interface AnswerMapper {

    Answer toEntity(AnswerRequestDto answerRequestDto);

    AnswerResponseDto toResponseDto(Answer answer);

}
