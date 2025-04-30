package com.gymforhealthy.gms.responseDto;


import com.gymforhealthy.gms.entity.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerCertificateResponseDto {

    private Integer id;
    private String certificateName;
    private String issuedBy;
    private LocalDate issuedDate;
    private Integer userId;

}
