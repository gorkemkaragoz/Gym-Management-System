package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.BodyMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BodyMeasurementRepository extends JpaRepository<BodyMeasurement, Long>{
}