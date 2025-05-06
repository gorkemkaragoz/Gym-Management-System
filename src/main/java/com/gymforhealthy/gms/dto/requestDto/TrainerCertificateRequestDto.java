package com.gymforhealthy.gms.dto.requestDto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerCertificateRequestDto {

    private String certificateName;
    private String issuedBy;
    private LocalDate issuedDate;
    private Integer userId;

}
