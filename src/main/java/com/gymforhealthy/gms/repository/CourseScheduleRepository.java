
package com.gymforhealthy.gms.repository;

import com.gymforhealthy.gms.entity.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseScheduleRepository extends JpaRepository<CourseSchedule, Long>{

}