package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{
    List<Answer> findByResponderId(Integer responderId);
}