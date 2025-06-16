package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.ChangePasswordRequestDto;
import com.gymforhealthy.gms.dto.requestDto.UserManagementRequestDto;
import com.gymforhealthy.gms.dto.requestDto.UserRequestDto;
import com.gymforhealthy.gms.dto.responseDto.UserManagementResponseDto;
import com.gymforhealthy.gms.dto.responseDto.UserResponseDto;
import com.gymforhealthy.gms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/management")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> createUserByAdmin(@RequestBody UserManagementRequestDto requestDto) {
        UserResponseDto savedUser = userService.createUserByAdmin(requestDto);
        return ResponseEntity.ok(savedUser); // Artık userId dönecek
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserManagementResponseDto> updatePartialUser(
            @PathVariable Long id,
            @RequestBody UserManagementRequestDto requestDto) {
        return ResponseEntity.ok(userService.updatePartialUser(id, requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.updateUser(id, userRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/trainers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserManagementResponseDto>> getAllTrainers() {
        return ResponseEntity.ok(userService.findAllByRoleName("TRAINER"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(Authentication authentication) {

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

    @GetMapping("/management")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserManagementResponseDto>> getAllMembersAndTrainers() {
        return ResponseEntity.ok(userService.getAllMembersAndTrainers());
    }

    @GetMapping("/management/me")
    public ResponseEntity<UserManagementResponseDto> getCurrentUserDetailed(Authentication authentication) {
        // authentication.getName() genellikle "username" veya "email" döner.
        // Sizin uygulamanızda login token içinde email varsa, bu email değeri burada gelir.
        String email = authentication.getName();
        UserManagementResponseDto response = userService.findByEmaill(email);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/change-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequestDto request, Authentication authentication) {
        String email = authentication.getName();
        userService.changePassword(email, request.getCurrentPassword(), request.getNewPassword());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}