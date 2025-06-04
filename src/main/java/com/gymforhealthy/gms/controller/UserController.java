package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.UserRequestDto;
import com.gymforhealthy.gms.dto.responseDto.UserResponseDto;
import com.gymforhealthy.gms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.saveUser(userRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.updateUser(id, userRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(Authentication authentication) {
        // authentication.getName() genellikle "username" veya "email" döner.
        // Sizin uygulamanızda login token içinde email varsa, bu email değeri burada gelir.
        String email = authentication.getName();
        UserResponseDto currentUserDto = userService.findByEmail(email);
        return ResponseEntity.ok(currentUserDto);
    }

    @GetMapping("/tcno/{tcNo}")
    public ResponseEntity<UserResponseDto> getUserByTcNo(@PathVariable String tcNo) {
        return ResponseEntity.ok(userService.findByTcNo(tcNo));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}