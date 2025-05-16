package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.UserPhotoRequestDto;
import com.gymforhealthy.gms.dto.responseDto.UserPhotoResponseDto;
import com.gymforhealthy.gms.service.UserPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-photo")
@RequiredArgsConstructor
public class UserPhotoController {

    private final UserPhotoService userPhotoService;

    @PostMapping
    public ResponseEntity<UserPhotoResponseDto> save(@RequestBody UserPhotoRequestDto userPhotoRequestDto) {
        return ResponseEntity.ok(userPhotoService.saveUserPhoto(userPhotoRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPhotoResponseDto> update(@PathVariable Long id, @RequestBody UserPhotoRequestDto userPhotoRequestDto) {
        return ResponseEntity.ok(userPhotoService.updateUserPhoto(id, userPhotoRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPhotoResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userPhotoService.findUserPhotoById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserPhotoResponseDto> delete(@PathVariable Long id) {
        userPhotoService.deleteUserPhoto(id);
        return ResponseEntity.noContent().build();
    }

}
