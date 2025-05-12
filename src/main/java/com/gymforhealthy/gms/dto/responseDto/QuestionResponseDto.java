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
    private String subjectType; // Enum → string olarak tutulur
    private String status;      // OPEN, IN_PROGRESS, RESOLVED
    private Long senderId;
}
