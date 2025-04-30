package com.gymforhealthy.gms.mapperImpl;

import com.gymforhealthy.gms.entity.Question;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.mapper.QuestionMapper;
import com.gymforhealthy.gms.requestDto.QuestionRequestDto;
import com.gymforhealthy.gms.responseDto.QuestionResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question toEntity(QuestionRequestDto questionRequestDto) {
        if (questionRequestDto == null) return null;

        Question question = new Question();
        question.setContent(questionRequestDto.getContent());

        // Enum dönüşümü: String → Enum
        question.setSubjectType(Question.SubjectType.valueOf(questionRequestDto.getSubjectType().toUpperCase()));

        // Sistem zamanı atanıyor
        question.setCreatedAt(LocalDateTime.now());

        // Başlangıç durumu "OPEN"
        question.setStatus(Question.Status.OPEN);

        // senderId'den User nesnesi oluşturup ilişkilendiriyoruz
        User sender = new User();
        sender.setId(questionRequestDto.getSenderId());
        question.setSender(sender);

        return question;
    }

    @Override
    public QuestionResponseDto toResponseDto(Question question) {
        if (question == null) return null;

        return QuestionResponseDto.builder()
                .id(question.getId())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .subjectType(question.getSubjectType().name()) // Enum → String
                .status(question.getStatus().name())           // Enum → String
                .senderId(question.getSender().getId())
                .build();
    }
}
