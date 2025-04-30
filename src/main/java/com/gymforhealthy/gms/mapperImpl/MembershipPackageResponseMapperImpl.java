package com.gymforhealthy.gms.mapperImpl;

import com.gymforhealthy.gms.entity.MembershipPackage;
import com.gymforhealthy.gms.mapper.MembershipPackageResponseMapper;
import com.gymforhealthy.gms.responseDto.MembershipPackageResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MembershipPackageResponseMapperImpl implements MembershipPackageResponseMapper {

    @Override
    public MembershipPackageResponseDto toResponseDto(MembershipPackage membershipPackage) {

       if (membershipPackage == null)return null;

       return MembershipPackageResponseDto.builder()
               .id(membershipPackage.getId())
               .name(membershipPackage.getName())
               .packageTotalHour(membershipPackage.getPackageTotalHour())
               .isUnlimited(membershipPackage.getIsUnlimited())
               .build();

    }
}
