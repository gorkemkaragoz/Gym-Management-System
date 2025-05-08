
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPhotoRepository extends JpaRepository<UserPhoto, Integer>{
    List<UserPhoto> findByUserId(Integer userId);
}