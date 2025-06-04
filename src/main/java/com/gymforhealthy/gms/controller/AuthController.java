package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.AuthRequest;
import com.gymforhealthy.gms.dto.responseDto.AuthResponse;
import com.gymforhealthy.gms.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final com.gymforhealthy.gms.security.CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        UserDetails user = customUserDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(user);
        String role = user.getAuthorities().iterator().next().getAuthority();

        return new AuthResponse(token, role);
    }
}
