package com.gymforhealthy.gms.dto.responseDto;


import com.gymforhealthy.gms.entity.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerCertificateResponseDto {

    private Long id;
    private String certificateName;
    private String issuedBy;
    private LocalDate issuedDate;
    private Long userId;

}
