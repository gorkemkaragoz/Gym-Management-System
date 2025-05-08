
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.ItemAllergen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemAllergenRepository extends JpaRepository<ItemAllergen, Integer>{
    List<ItemAllergen> findByItemId(Integer itemId);
}