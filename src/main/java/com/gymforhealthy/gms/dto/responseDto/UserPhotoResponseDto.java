package com.gymforhealthy.gms.dto.responseDto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPhotoResponseDto {

    private Integer id;
    private byte[] photo;
    private LocalDateTime uploadedAt;
    private Integer userId;

}
