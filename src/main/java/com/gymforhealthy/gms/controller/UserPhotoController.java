package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.UserPhotoRequestDto;
import com.gymforhealthy.gms.dto.responseDto.UserPhotoResponseDto;
import com.gymforhealthy.gms.service.UserPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-photos")
@RequiredArgsConstructor
public class UserPhotoController {

    private final UserPhotoService userPhotoService;

    @PostMapping
    public ResponseEntity<UserPhotoResponseDto> uploadPhoto(@RequestBody UserPhotoRequestDto requestDto) {
        return ResponseEntity.ok(userPhotoService.upload(requestDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserPhotoResponseDto>> getPhotosByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(userPhotoService.findByUserId(userId));
    }

    @GetMapping("/{photoId}")
    public ResponseEntity<UserPhotoResponseDto> getPhotoById(@PathVariable Integer photoId) {
        return ResponseEntity.ok(userPhotoService.findById(photoId));
    }

    @DeleteMapping("/{photoId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Integer photoId) {
        userPhotoService.delete(photoId);
        return ResponseEntity.noContent().build();
    }
}
