package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.CourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {

    boolean existsByUserIdAndCourseScheduleId(Long userId, Long courseScheduleId);

    @Query("""
    SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END
    FROM CourseEnrollment e
    WHERE e.user.id = :userId
      AND e.courseSchedule.courseDate = :date
      AND (
            e.courseSchedule.startTime < :endTime
        AND e.courseSchedule.endTime > :startTime
      )
      AND e.status = 'ACTIVE'""")
    boolean existsUserScheduleConflict(
            @Param("userId") Long userId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );
    long countByCourseScheduleId(Long courseScheduleId);

    void deleteByUserIdAndCourseScheduleId(Long userId, Long courseScheduleId);

    List<CourseEnrollment> findByUserId(Long userId);
}