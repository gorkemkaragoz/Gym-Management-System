package com.gymforhealthy.gms.requestDto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BodyMeasurementRequestDto {

    private BigDecimal weight;
    private Integer userId;

}
