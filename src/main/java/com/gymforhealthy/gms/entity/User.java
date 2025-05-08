package com.gymforhealthy.gms.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "tc_no", unique = true, nullable = false)
    private String tcNo;

    @Column(name = "account_locked")
    private Boolean accountLocked = false;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<UserPhoto> userPhotos;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Course> trainerCourses;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CourseSchedule> trainerSchedules;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TrainerCertificate> trainerCertificates;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CourseEnrollment> courseEnrollments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BodyMeasurement> bodyMeasurements;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Question> sentQuestions;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<QuestionAssignment> receivedAssignments;

    @OneToMany(mappedBy = "responder", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Answer> givenAnswers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Membership> memberships;

}