package com.gymforhealthy.gms.mapper;

import com.gymforhealthy.gms.entity.Question;
import com.gymforhealthy.gms.requestDto.QuestionRequestDto;
import com.gymforhealthy.gms.responseDto.QuestionResponseDto;

public interface QuestionMapper {

    // DTO'dan entity'ye dönüşüm
    Question toEntity(QuestionRequestDto questionRequestDto);

    // entity'den DTO'ya dönüşüm
    QuestionResponseDto toResponseDto(Question question);

}
