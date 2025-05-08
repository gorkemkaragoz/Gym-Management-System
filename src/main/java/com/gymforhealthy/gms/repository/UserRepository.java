
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByTcNo(String tcNo);
}