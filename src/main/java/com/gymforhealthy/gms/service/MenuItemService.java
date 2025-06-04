package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.responseDto.MenuItemResponseDto;

import java.util.List;

public interface MenuItemService {
    List<MenuItemResponseDto> findAllMenuItem();
    MenuItemResponseDto findMenuItemById(Long id);
}
