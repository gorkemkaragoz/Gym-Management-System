package com.gymforhealthy.gms.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "max_capacity")
    private Integer maxCapacity = 25;

    @ManyToOne
    @JoinColumn(name = "trainer_user_id")
    @JsonBackReference
    private User trainer;

    @JsonManagedReference
    @OneToMany(mappedBy = "course")
    private List<CourseSchedule> schedules;

    @JsonManagedReference
    @OneToMany(mappedBy = "course")
    private List<CourseEnrollment> enrollments;
}