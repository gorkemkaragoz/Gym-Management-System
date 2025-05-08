
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
    List<Question> findBySenderId(Integer senderId);
}