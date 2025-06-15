package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.responseDto.MenuItemResponseDto;
import com.gymforhealthy.gms.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @GetMapping
    public ResponseEntity<List<MenuItemResponseDto>> findAllMenuItem() {
        return ResponseEntity.ok(menuItemService.findAllMenuItem());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponseDto> getMenuItemId(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.findMenuItemById(id));
    }
}