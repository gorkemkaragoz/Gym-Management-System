package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.requestDto.MembershipRequestDto;
import com.gymforhealthy.gms.dto.responseDto.MembershipResponseDto;
import com.gymforhealthy.gms.service.MembershipService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/membership")
@AllArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    @PostMapping
    public ResponseEntity<MembershipResponseDto> save(@RequestBody MembershipRequestDto membershipRequestDto) {
        // Yeni üyelik kaydeder ve DTO olarak döner
        return ResponseEntity.ok(membershipService.saveMembership(membershipRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembershipResponseDto> update(@PathVariable Long id, @RequestBody MembershipRequestDto membershipRequestDto) {
        // Belirtilen ID ile üyeliği günceller ve DTO olarak döner
        return ResponseEntity.ok(membershipService.updateMembership(id, membershipRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<MembershipResponseDto>> getAll() {
        // Tüm üyelikleri liste olarak döner
        return ResponseEntity.ok(membershipService.findAllMemberships());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipResponseDto> getById(@PathVariable Long id) {
        // Belirtilen ID ile üyeliği bulur ve DTO olarak döner
        return ResponseEntity.ok(membershipService.findMembershipById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Belirtilen ID ile üyeliği siler
        membershipService.deleteMembership(id);
        return ResponseEntity.noContent().build();
    }
}