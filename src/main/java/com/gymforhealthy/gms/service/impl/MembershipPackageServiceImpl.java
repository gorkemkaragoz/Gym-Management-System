package com.gymforhealthy.gms.service.impl;

import com.gymforhealthy.gms.repository.MembershipPackageRepository;
import com.gymforhealthy.gms.dto.responseDto.MembershipPackageResponseDto;
import com.gymforhealthy.gms.service.MembershipPackageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipPackageServiceImpl implements MembershipPackageService {

    private final ModelMapper modelMapper;
    private final MembershipPackageRepository membershipPackageRepository;

    @Override
    public List<MembershipPackageResponseDto> findAll() {
        return membershipPackageRepository.findAll()
                .stream()
                .map(membershipPackage -> modelMapper.map(membershipPackage,MembershipPackageResponseDto.class))
                .collect(Collectors.toList());
    }
}
