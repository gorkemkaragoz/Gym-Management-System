
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.CourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {
    long countByCourseId(Long id);
}
