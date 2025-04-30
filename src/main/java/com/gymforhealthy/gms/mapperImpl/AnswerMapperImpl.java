package com.gymforhealthy.gms.mapperImpl;

import com.gymforhealthy.gms.entity.Answer;
import com.gymforhealthy.gms.entity.Question;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.mapper.AnswerMapper;
import com.gymforhealthy.gms.requestDto.AnswerRequestDto;
import com.gymforhealthy.gms.responseDto.AnswerResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer toEntity(AnswerRequestDto answerRequestDto) {
        if (answerRequestDto == null) return null;

        Answer answer = new Answer();
        answer.setResponse(answerRequestDto.getResponse());
        answer.setRespondedAt(LocalDateTime.now());

        Question question = new Question();
        question.setId(answerRequestDto.getQuestionId());
        answer.setQuestion(question);

        User responder = new User();
        responder.setId(answerRequestDto.getResponderId());
        answer.setResponder(responder);

        return answer;
    }

    @Override
    public AnswerResponseDto toResponseDto(Answer answer) {
        if (answer == null) return null;

        return AnswerResponseDto.builder()
                .id(answer.getId())
                .response(answer.getResponse())
                .respondedAt(answer.getRespondedAt())
                .questionId(answer.getQuestion().getId())
                .responderId(answer.getResponder().getId())
                .build();
    }
}
