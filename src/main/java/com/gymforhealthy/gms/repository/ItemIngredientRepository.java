
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.ItemIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemIngredientRepository extends JpaRepository<ItemIngredient, Integer>{
    List<ItemIngredient> findByItemId(Integer itemId);
}