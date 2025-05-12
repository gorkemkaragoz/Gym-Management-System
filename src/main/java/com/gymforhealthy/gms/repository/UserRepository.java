
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByTcNo(String tcNo);
}