package com.gymforhealthy.gms.responseDto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemResponseDto {

    private Integer id;
    private String name;
    private BigDecimal price;
    private String typeName; // "Protein", "Vitamin", "Drink" gibi

}
