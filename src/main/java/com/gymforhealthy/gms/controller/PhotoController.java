// src/main/java/com/gymforhealthy/gms/controller/PhotoController.java
package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.entity.UserPhoto;
import com.gymforhealthy.gms.repository.UserPhotoRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final S3Service s3Service;
    private final UserRepository userRepository;
    private final UserPhotoRepository userPhotoRepository;

    /**
     * Uploads a profile photo to S3, then saves its URL in the database linked to the given userId
     */
    @PostMapping("/upload/{userId}")
    public ResponseEntity<String> uploadPhoto(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            // 1. Upload to S3
            String url = s3Service.uploadFile(file);

            // 2. Load user
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

            // 3. Save URL in UserPhoto
            UserPhoto photo = new UserPhoto();
            photo.setUser(user);
            photo.setPhotoUrl(url);
            photo.setUploadedAt(LocalDateTime.now());
            userPhotoRepository.save(photo);

            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }
}