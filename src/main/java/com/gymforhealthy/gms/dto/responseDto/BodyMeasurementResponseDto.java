package com.gymforhealthy.gms.dto.responseDto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BodyMeasurementResponseDto {

    private Integer id;
    private BigDecimal height;
    private BigDecimal weight;
    private BigDecimal bmi;
    private LocalDateTime createdTime;
    private Integer userId;

}
