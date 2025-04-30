package com.gymforhealthy.gms.mapper;

import com.gymforhealthy.gms.entity.Membership;
import com.gymforhealthy.gms.entity.MembershipPackage;
import com.gymforhealthy.gms.requestDto.MembershipRequestDto;
import com.gymforhealthy.gms.responseDto.MembershipPackageResponseDto;
import com.gymforhealthy.gms.responseDto.MembershipResponseDto;

public interface MembershipMapper {

    // DTO'dan entity'ye dönüşüm
    Membership toEntity(MembershipRequestDto membershipRequestDto);

    // entity'den DTO'ya dönüşüm
    MembershipResponseDto toResponseDto(Membership membership);
}
