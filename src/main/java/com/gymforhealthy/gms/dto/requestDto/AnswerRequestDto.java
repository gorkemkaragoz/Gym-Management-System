package com.gymforhealthy.gms.dto.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerRequestDto {

    private String response;
    private Long questionId;
    private Long responderId;
}
