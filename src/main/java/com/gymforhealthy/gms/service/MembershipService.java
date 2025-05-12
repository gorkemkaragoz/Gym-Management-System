package com.gymforhealthy.gms.service;

import com.gymforhealthy.gms.dto.requestDto.MembershipRequestDto;
import com.gymforhealthy.gms.dto.responseDto.MembershipResponseDto;

import java.util.List;

public interface MembershipService {

    MembershipResponseDto saveMembership(MembershipRequestDto membershipRequestDto);
    MembershipResponseDto updateMembership(Long id, MembershipRequestDto membershipRequestDto);
    List<MembershipResponseDto> findAllMemberships();
    MembershipResponseDto findMembershipById(Long id);
    void deleteMembership(Long id);
}
