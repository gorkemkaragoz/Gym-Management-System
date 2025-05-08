package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.repository.MenuItemRepository;
import com.gymforhealthy.gms.dto.responseDto.MenuItemResponseDto;
import com.gymforhealthy.gms.service.MenuItemService;
import com.gymforhealthy.gms.entity.MenuItem;
import com.gymforhealthy.gms.repository.ItemAllergenRepository;
import com.gymforhealthy.gms.repository.ItemIngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final ItemAllergenRepository itemAllergenRepository;
    private final ItemIngredientRepository itemIngredientRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<MenuItemResponseDto> findAll() {
        List<MenuItem> menuItems = menuItemRepository.findAll();

        return menuItems.stream().map(menuItem -> {
            MenuItemResponseDto dto = modelMapper.map(menuItem, MenuItemResponseDto.class);
            dto.setTypeName(menuItem.getType() != null ? menuItem.getType().getName() : null);
            dto.setIngredients(itemIngredientRepository.findByItemId(menuItem.getId())
                    .stream()
                    .map(itemIngredient -> itemIngredient.getIngredientName())
                    .collect(Collectors.toList()));
            dto.setAllergens(itemAllergenRepository.findByItemId(menuItem.getId())
                    .stream()
                    .map(itemAllergen -> itemAllergen.getAllergenName())
                    .collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());

    }

}