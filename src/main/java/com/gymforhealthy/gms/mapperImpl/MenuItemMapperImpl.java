package com.gymforhealthy.gms.mapperImpl;

import com.gymforhealthy.gms.entity.MenuItem;
import com.gymforhealthy.gms.mapper.MenuItemMapper;
import com.gymforhealthy.gms.responseDto.MenuItemResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MenuItemMapperImpl implements MenuItemMapper {

    @Override
    public MenuItemResponseDto toResponseDto(MenuItem menuItem) {
    if(menuItem == null)  return null;

    return MenuItemResponseDto.builder()
            .id(menuItem.getId())
            .name(menuItem.getName())
            .price(menuItem.getPrice())
            .typeName(menuItem.getType() != null ? menuItem.getType().getName() : null)
            .build();

    }
}
