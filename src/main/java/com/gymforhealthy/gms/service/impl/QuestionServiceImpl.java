package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.QuestionRequestDto;
import com.gymforhealthy.gms.dto.responseDto.QuestionResponseDto;
import com.gymforhealthy.gms.entity.Question;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.entity.Question.SubjectType;
import com.gymforhealthy.gms.repository.QuestionRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public QuestionResponseDto save(QuestionRequestDto requestDto) {
        User sender = userRepository.findById(requestDto.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Question question = new Question();
        question.setContent(requestDto.getContent());
        question.setCreatedAt(LocalDateTime.now());
        question.setSubjectType(Question.SubjectType.valueOf(requestDto.getSubjectType()));
        question.setStatus(Question.Status.OPEN);
        question.setSender(sender);

        Question saved = questionRepository.save(question);
        return modelMapper.map(saved, QuestionResponseDto.class);
    }

    @Override
    public QuestionResponseDto update(Integer id, QuestionRequestDto requestDto) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        question.setContent(requestDto.getContent());
        question.setSubjectType(Question.SubjectType.valueOf(requestDto.getSubjectType()));

        return modelMapper.map(questionRepository.save(question), QuestionResponseDto.class);
    }

    @Override
    public QuestionResponseDto findById(Integer id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        return modelMapper.map(question, QuestionResponseDto.class);
    }

    @Override
    public List<QuestionResponseDto> findAll() {
        return questionRepository.findAll()
                .stream()
                .map(q -> modelMapper.map(q, QuestionResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionResponseDto> findBySenderId(Integer senderId) {
        return questionRepository.findBySenderId(senderId)
                .stream()
                .map(q -> modelMapper.map(q, QuestionResponseDto.class))
                .collect(Collectors.toList());
    }

}
