package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.dto.responseDto.MenuItemResponseDto;
import com.gymforhealthy.gms.entity.MenuItem;
import com.gymforhealthy.gms.repository.MenuItemRepository;
import com.gymforhealthy.gms.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<MenuItemResponseDto> findAllMenuItem() {
        return menuItemRepository.findAll().stream()
                .map(menuItem -> convertToResponseDto(menuItem))
                .toList();
    }

    private MenuItemResponseDto convertToResponseDto(MenuItem menuItem) {
        MenuItemResponseDto menuItemResponseDto = modelMapper.map(menuItem, MenuItemResponseDto.class);

        menuItemResponseDto.setTypeName(menuItem.getType().getName());

        menuItemResponseDto.setIngredients(menuItem.getIngredients().stream()
                .map(ingredient -> ingredient.getIngredientName())
                .toList());

        menuItemResponseDto.setAllergens(menuItem.getAllergens().stream()
                .map(allergen -> allergen.getAllergenName())
                .toList());

        return menuItemResponseDto;
    }
}