package com.gymforhealthy.gms.dto.responseDto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponseDto {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String subjectType;
    private String status;
    private Long senderId;
    private Long recipientId;
}
