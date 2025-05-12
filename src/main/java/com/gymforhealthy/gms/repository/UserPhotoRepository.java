
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserPhotoRepository extends JpaRepository<UserPhoto, Long>{
}