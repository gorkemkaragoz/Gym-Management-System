
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseScheduleRepository extends JpaRepository<CourseSchedule, Long>{

    List<CourseSchedule> findByCourseDateAndTrainerId(LocalDate courseDate, Long trainerId);
}