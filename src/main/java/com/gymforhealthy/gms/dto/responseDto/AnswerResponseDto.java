package com.gymforhealthy.gms.dto.responseDto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResponseDto {

    private Long id;
    private String response;
    private LocalDateTime respondedAt;
    private Long questionId;
    private Long responderId;
}
