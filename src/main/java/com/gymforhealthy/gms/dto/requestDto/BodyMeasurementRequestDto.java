package com.gymforhealthy.gms.dto.requestDto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BodyMeasurementRequestDto {

    private BigDecimal height;
    private BigDecimal weight;
    private Long userId;

}
