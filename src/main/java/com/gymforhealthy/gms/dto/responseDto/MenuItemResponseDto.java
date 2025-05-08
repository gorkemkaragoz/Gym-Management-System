package com.gymforhealthy.gms.dto.responseDto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemResponseDto {

    private Integer id;
    private String name;
    private BigDecimal price;
    private String typeName;
    private List<String> ingredients;
    private List<String> allergens;

}