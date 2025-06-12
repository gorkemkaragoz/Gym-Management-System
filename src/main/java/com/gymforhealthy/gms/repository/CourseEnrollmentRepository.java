package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.CourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {

    boolean existsByUserIdAndCourseScheduleId(Long userId, Long courseScheduleId);

    long countByCourseScheduleId(Long courseScheduleId);

    void deleteByUserIdAndCourseScheduleId(Long userId, Long courseScheduleId);

    List<CourseEnrollment> findByUserId(Long userId);
}