
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.QuestionAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionAssignmentRepository extends JpaRepository<QuestionAssignment, Long>{
    Optional<QuestionAssignment> findByQuestionId(Long questionId);
}