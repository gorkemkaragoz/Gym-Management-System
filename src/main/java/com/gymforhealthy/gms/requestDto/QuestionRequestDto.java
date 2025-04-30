package com.gymforhealthy.gms.requestDto;

import com.gymforhealthy.gms.entity.Question;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionRequestDto {

    private String content;
    private String subjectType; // SUGGESTION, COMPLAINT, QUESTION
    private Integer senderId;

}
