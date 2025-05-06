package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.responseDto.MembershipPackageResponseDto;

import java.util.List;

public interface MembershipPackageService {
    List<MembershipPackageResponseDto> findAll();
}
