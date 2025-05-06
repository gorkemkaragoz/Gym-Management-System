package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.MembershipRequestDto;
import com.gymforhealthy.gms.dto.responseDto.MembershipResponseDto;

import java.util.List;

public interface MembershipService {
    MembershipResponseDto save(MembershipRequestDto membershipRequestDto);
    MembershipResponseDto update(Integer id, MembershipRequestDto membershipRequestDto);
    void delete(Integer id);
    MembershipResponseDto findById(Integer id);
    List<MembershipResponseDto> findAll();
}
