package com.gymforhealthy.gms.controller;

import com.gymforhealthy.gms.dto.responseDto.MembershipPackageResponseDto;
import com.gymforhealthy.gms.service.MembershipPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/membership-package")
public class MembershipPackageController {

    private final MembershipPackageService membershipPackageService;

    @Autowired
    public MembershipPackageController(MembershipPackageService membershipPackageService) {
        this.membershipPackageService = membershipPackageService;
    }

    @GetMapping
    public ResponseEntity<List<MembershipPackageResponseDto>> findAll() {
        return ResponseEntity.ok(membershipPackageService.findAll());
    }

}
