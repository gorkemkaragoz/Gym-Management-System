package com.gymforhealthy.gms.responseDto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResponseDto {

    private Integer id;
    private String response;
    private LocalDateTime respondedAt;
    private Integer questionId;
    private Integer responderId;
}
