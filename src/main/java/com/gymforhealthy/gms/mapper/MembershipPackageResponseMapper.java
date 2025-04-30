package com.gymforhealthy.gms.mapper;

import com.gymforhealthy.gms.entity.MembershipPackage;
import com.gymforhealthy.gms.responseDto.MembershipPackageResponseDto;

public interface MembershipPackageResponseMapper {

    // entity'den DTO'ya dönüşüm
    MembershipPackageResponseDto toResponseDto(MembershipPackage membershipPackage);


}
