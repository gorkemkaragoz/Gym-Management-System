
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.TrainerCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainerCertificateRepository extends JpaRepository<TrainerCertificate, Long>{
}