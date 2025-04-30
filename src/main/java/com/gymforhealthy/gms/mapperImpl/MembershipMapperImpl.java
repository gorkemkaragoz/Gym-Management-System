package com.gymforhealthy.gms.mapperImpl;

import com.gymforhealthy.gms.entity.Membership;
import com.gymforhealthy.gms.entity.MembershipPackage;
import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.mapper.MembershipMapper;
import com.gymforhealthy.gms.requestDto.MembershipRequestDto;
import com.gymforhealthy.gms.responseDto.MembershipResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapperImpl implements MembershipMapper {

    @Override
    public Membership toEntity(MembershipRequestDto membershipRequestDto) {
        if (membershipRequestDto == null) return null;

        Membership membership = new Membership();
        membership.setStartDate(membershipRequestDto.getStartDate());
        membership.setEndDate(membershipRequestDto.getEndDate());
        membership.setMembershipStatus(Membership.MembershipStatus.ACTIVE); // varsayılan durum

        // User ilişkisi
        User user = new User();
        user.setId(membershipRequestDto.getUserId());
        membership.setUser(user);

        // Package ilişkisi
        MembershipPackage membershipPackage = new MembershipPackage();
        membershipPackage.setId(membershipRequestDto.getPackageId());
        membership.setPackageId(membershipPackage);

        return membership;
    }

    @Override
    public MembershipResponseDto toResponseDto(Membership membership) {
        if (membership == null) return null;

        return MembershipResponseDto.builder()
                .id(membership.getId())
                .remainingHours(membership.getRemainingHours())
                .startDate(membership.getStartDate())
                .endDate(membership.getEndDate())
                .membershipStatus(membership.getMembershipStatus().name()) // enum → string
                .userId(membership.getUser().getId())
                .packageName(membership.getPackageId().getName())
                .build();
    }
}
