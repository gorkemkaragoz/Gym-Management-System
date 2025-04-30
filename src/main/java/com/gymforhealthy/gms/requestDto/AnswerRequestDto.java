package com.gymforhealthy.gms.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerRequestDto {

    private String response;
    private Integer questionId;
    private Integer responderId;
}
