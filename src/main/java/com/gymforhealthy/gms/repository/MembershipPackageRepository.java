
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.MembershipPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipPackageRepository extends JpaRepository<MembershipPackage, Long>{

}