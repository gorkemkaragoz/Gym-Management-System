//package com.gymforhealthy.gms.service.impl;
//
//import com.gymforhealthy.gms.dto.requestDto.QuestionRequestDto;
//import com.gymforhealthy.gms.dto.responseDto.QuestionResponseDto;
//import com.gymforhealthy.gms.entity.Question;
//import com.gymforhealthy.gms.entity.Question.Status;
//import com.gymforhealthy.gms.entity.Question.SubjectType;
//import com.gymforhealthy.gms.entity.QuestionAssignment;
//import com.gymforhealthy.gms.entity.User;
//import com.gymforhealthy.gms.exception.ResourceNotFoundException;
//import com.gymforhealthy.gms.repository.QuestionAssignmentRepository;
//import com.gymforhealthy.gms.repository.QuestionRepository;
//import com.gymforhealthy.gms.repository.UserRepository;
//import com.gymforhealthy.gms.service.QuestionService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class QuestionServiceImpl implements QuestionService {
//
//    private final QuestionRepository questionRepository;
//    private final QuestionAssignmentRepository questionAssignmentRepository;
//    private final UserRepository userRepository;
//
//    @Override
//    public QuestionResponseDto saveQuestion(QuestionRequestDto requestDto) {
//        User sender = userRepository.findById(requestDto.getSenderId())
//                .orElseThrow(() -> new ResourceNotFoundException("Sender not found"));
//
//        User recipient = userRepository.findById(requestDto.getRecipientId())
//                .orElseThrow(() -> new ResourceNotFoundException("Recipient not found"));
//
//        String senderRole = sender.getRole().getName().toUpperCase();
//        String recipientRole = recipient.getRole().getName().toUpperCase();
//
//        // Üye sadece eğitmen ya da yöneticiye yazabilir
//        if (senderRole.equals("MEMBER") &&
//                !(recipientRole.equals("TRAINER") || recipientRole.equals("ADMIN"))) {
//            throw new IllegalArgumentException("Member can only send messages to trainer or admin.");
//        }
//
//        // Eğitmen ve yönetici mesaj başlatamaz
//        if ((senderRole.equals("TRAINER") || senderRole.equals("ADMIN")) &&
//                requestDto.getSubjectType() != null) {
//            throw new IllegalArgumentException("Trainers and admins cannot initiate a message.");
//        }
//
//        // Eğitmen sadece soru alabilir
//        if (recipientRole.equals("TRAINER") &&
//                !SubjectType.QUESTION.name().equals(requestDto.getSubjectType())) {
//            throw new IllegalArgumentException("Trainer can only receive questions.");
//        }
//
//        Question question = new Question();
//        question.setContent(requestDto.getContent());
//        question.setCreatedAt(LocalDateTime.now());
//        question.setSubjectType(SubjectType.valueOf(requestDto.getSubjectType()));
//        question.setStatus(Status.OPEN);
//        question.setSender(sender);
//
//        question = questionRepository.save(question);
//
//        QuestionAssignment assignment = new QuestionAssignment();
//        assignment.setQuestion(question);
//        assignment.setRecipient(recipient);
//        questionAssignmentRepository.save(assignment);
//
//        return convertToResponseDto(question);
//    }
//
//    @Override
//    public QuestionResponseDto findQuestionById(Long id) {
//        Question question = questionRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
//        return convertToResponseDto(question);
//    }
//
//    @Override
//    public void deleteQuestion(Long id) {
//        Question question = questionRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
//        questionRepository.delete(question);
//    }
//
//    private QuestionResponseDto convertToResponseDto(Question question) {
//        return QuestionResponseDto.builder()
//                .id(question.getId())
//                .content(question.getContent())
//                .createdAt(question.getCreatedAt())
//                .subjectType(question.getSubjectType().name()) // Enum -> String
//                .status(question.getStatus().name())           // Enum -> String
//                .senderId(question.getSender().getId())
//                .build();
//    }
//}

package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.requestDto.QuestionRequestDto;
import com.gymforhealthy.gms.dto.responseDto.QuestionResponseDto;
import com.gymforhealthy.gms.entity.Question;
import com.gymforhealthy.gms.entity.Question.Status;
import com.gymforhealthy.gms.entity.Question.SubjectType;
import com.gymforhealthy.gms.entity.QuestionAssignment;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.exception.ResourceNotFoundException;
import com.gymforhealthy.gms.repository.QuestionAssignmentRepository;
import com.gymforhealthy.gms.repository.QuestionRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionAssignmentRepository questionAssignmentRepository;
    private final UserRepository userRepository;

    @Override
    public QuestionResponseDto saveQuestion(QuestionRequestDto requestDto) {
        User sender = userRepository.findById(requestDto.getSenderId())
                .orElseThrow(() -> new ResourceNotFoundException("Sender not found"));

        User recipient = userRepository.findById(requestDto.getRecipientId())
                .orElseThrow(() -> new ResourceNotFoundException("Recipient not found"));

        String senderRole = sender.getRole().getName().toUpperCase();
        String recipientRole = recipient.getRole().getName().toUpperCase();

        if (senderRole.equals("MEMBER") &&
                !(recipientRole.equals("TRAINER") || recipientRole.equals("ADMIN"))) {
            throw new IllegalArgumentException("Member can only send questions to trainer or admin.");
        }

        if ((senderRole.equals("TRAINER") || senderRole.equals("ADMIN")) &&
                requestDto.getSubjectType() != null) {
            throw new IllegalArgumentException("Trainer or admin cannot initiate message.");
        }

        if (recipientRole.equals("TRAINER") &&
                !requestDto.getSubjectType().equalsIgnoreCase("QUESTION")) {
            throw new IllegalArgumentException("Trainer can only receive questions.");
        }

        Question question = new Question();
        question.setContent(requestDto.getContent());
        question.setCreatedAt(LocalDateTime.now());
        question.setSubjectType(SubjectType.valueOf(requestDto.getSubjectType()));
        question.setStatus(Status.IN_PROGRESS);
        question.setSender(sender);

        question = questionRepository.save(question);

        QuestionAssignment assignment = new QuestionAssignment();
        assignment.setQuestion(question);
        assignment.setRecipient(recipient);
        questionAssignmentRepository.save(assignment);

        return convertToResponseDto(question);
    }

    @Override
    public QuestionResponseDto findQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        return convertToResponseDto(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        questionRepository.delete(question);
    }

    private QuestionResponseDto convertToResponseDto(Question question) {
        QuestionAssignment assignment = questionAssignmentRepository.findByQuestionId(question.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found for question id: " + question.getId()));

        return QuestionResponseDto.builder()
                .id(question.getId())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .subjectType(question.getSubjectType().name()) // Enum -> String
                .status(question.getStatus().name())           // Enum -> String
                .senderId(question.getSender().getId())
                .recipientId(assignment.getRecipient().getId())
                .build();
    }
}