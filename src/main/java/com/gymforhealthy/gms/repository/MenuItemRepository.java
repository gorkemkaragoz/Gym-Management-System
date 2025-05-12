package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}