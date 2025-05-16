package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.AnswerRequestDto;
import com.gymforhealthy.gms.dto.responseDto.AnswerResponseDto;
import com.gymforhealthy.gms.entity.Answer;
import com.gymforhealthy.gms.entity.Question;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.exception.ResourceNotFoundException;
import com.gymforhealthy.gms.repository.AnswerRepository;
import com.gymforhealthy.gms.repository.QuestionRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Override
    public AnswerResponseDto saveAnswer(AnswerRequestDto requestDto) {

        User responder = userRepository.findById(requestDto.getResponderId())
                .orElseThrow(() -> new ResourceNotFoundException("Responder not found with id: " + requestDto.getResponderId()));

        Question question = questionRepository.findById(requestDto.getQuestionId())
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + requestDto.getQuestionId()));

        String roleName = responder.getRole().getName().toUpperCase();
        if (!roleName.equals("TRAINER") && !roleName.equals("ADMIN")) {
            throw new IllegalArgumentException("Only TRAINER or ADMIN can send an answer.");
        }

        // Cevap verildiyse, sorunun durumu RESOLVED olsun
        question.setStatus(Question.Status.RESOLVED);
        questionRepository.save(question);


        Answer answer = new Answer();
        answer.setResponse(requestDto.getResponse());
        answer.setRespondedAt(LocalDateTime.now());
        answer.setResponder(responder);
        answer.setQuestion(question);

        answer = answerRepository.save(answer);

        return convertToResponseDto(answer);
    }

    @Override
    public AnswerResponseDto findAnswerById(Long id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with id: " + id));
        return convertToResponseDto(answer);
    }

    @Override
    public void deleteAnswer(Long id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer not found with id: " + id));
        answerRepository.delete(answer);
    }

    private AnswerResponseDto convertToResponseDto(Answer answer) {
        return AnswerResponseDto.builder()
                .id(answer.getId())
                .response(answer.getResponse())
                .respondedAt(answer.getRespondedAt())
                .questionId(answer.getQuestion().getId())
                .responderId(answer.getResponder().getId())
                .build();
    }
}