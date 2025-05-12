
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.ItemAllergen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemAllergenRepository extends JpaRepository<ItemAllergen, Long>{
}