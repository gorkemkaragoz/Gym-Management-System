/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.MembershipPackage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gorkem.karagoz
 */
public interface MembershipPackageRepository extends JpaRepository<MembershipPackage, Integer>{
    
}
