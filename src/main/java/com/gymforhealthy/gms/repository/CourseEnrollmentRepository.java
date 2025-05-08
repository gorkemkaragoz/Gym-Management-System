
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.CourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Integer> {

    Optional<CourseEnrollment> findByUserIdAndCourseId(Integer userId, Integer courseId);

    boolean existsByUserIdAndCourseIdAndStatus(Integer userId, Integer courseId, CourseEnrollment.Status status);

}
