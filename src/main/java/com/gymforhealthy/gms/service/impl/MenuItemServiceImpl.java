package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.repository.MenuItemRepository;
import com.gymforhealthy.gms.dto.responseDto.MenuItemResponseDto;
import com.gymforhealthy.gms.service.MenuItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<MenuItemResponseDto> findAll() {
        return menuItemRepository.findAll()
                .stream()
                .map(menuItem -> {
                    MenuItemResponseDto dto = modelMapper.map(menuItem, MenuItemResponseDto.class);
                    dto.setTypeName(menuItem.getType() != null ? menuItem.getType().getName() : null);
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
