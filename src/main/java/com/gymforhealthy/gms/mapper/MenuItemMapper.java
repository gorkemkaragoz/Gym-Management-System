package com.gymforhealthy.gms.mapper;

import com.gymforhealthy.gms.responseDto.MenuItemResponseDto;
import com.gymforhealthy.gms.entity.MenuItem;

public interface MenuItemMapper {

    MenuItemResponseDto toResponseDto(MenuItem menuItem);

}
