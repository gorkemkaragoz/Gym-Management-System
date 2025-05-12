
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.ItemIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemIngredientRepository extends JpaRepository<ItemIngredient, Long>{
}