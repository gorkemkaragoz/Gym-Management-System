package com.gymforhealthy.gms.dto.requestDto;

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
