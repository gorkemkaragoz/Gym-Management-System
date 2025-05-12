package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.FoodDrinkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodDrinkTypeRepository extends JpaRepository<FoodDrinkType, Long>{

}