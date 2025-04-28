/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    
}
