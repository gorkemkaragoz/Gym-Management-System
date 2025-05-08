package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.AnswerRequestDto;
import com.gymforhealthy.gms.dto.responseDto.AnswerResponseDto;
import com.gymforhealthy.gms.entity.Answer;
import com.gymforhealthy.gms.entity.Question;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.repository.AnswerRepository;
import com.gymforhealthy.gms.repository.QuestionRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public AnswerResponseDto save(AnswerRequestDto answerRequestDto) {
        Question question = questionRepository.findById(answerRequestDto.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        User responder = userRepository.findById(answerRequestDto.getResponderId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Answer answer = new Answer();
        answer.setResponse(answerRequestDto.getResponse());
        answer.setRespondedAt(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setResponder(responder);

        Answer savedAnswer = answerRepository.save(answer);
        return modelMapper.map(savedAnswer, AnswerResponseDto.class);
    }

    @Override
    public AnswerResponseDto findById(Integer id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Answer not found"));
        return modelMapper.map(answer, AnswerResponseDto.class);
    }

    @Override
    public List<AnswerResponseDto> findAll() {
        return answerRepository.findAll()
                .stream()
                .map(answer -> modelMapper.map(answer, AnswerResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnswerResponseDto> findByResponderId(Integer responderId) {
        return answerRepository.findByResponderId(responderId)
                .stream()
                .map(answer -> modelMapper.map(answer, AnswerResponseDto.class))
                .collect(Collectors.toList());
    }

}
