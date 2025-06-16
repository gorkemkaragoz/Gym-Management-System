
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByTcNo(String tcNo);
    Optional<User> findByEmail(String email);
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userPhotos")
    List<User> findAllUsersWithPhotos();
}