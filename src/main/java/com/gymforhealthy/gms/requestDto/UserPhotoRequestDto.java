package com.gymforhealthy.gms.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPhotoRequestDto {

    private byte[] photo;
    private Integer userId;

}
