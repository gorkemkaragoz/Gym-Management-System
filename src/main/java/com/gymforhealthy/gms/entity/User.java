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

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<UserPhoto> userPhotos;

    @JsonManagedReference
    @OneToMany(mappedBy = "trainer")
    private List<Course> trainerCourses;

    @JsonManagedReference
    @OneToMany(mappedBy = "trainer")
    private List<CourseSchedule> trainerSchedules;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<TrainerCertificate> trainerCertificates;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<CourseEnrollment> courseEnrollments;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<BodyMeasurement> bodyMeasurements;

    @JsonManagedReference
    @OneToMany(mappedBy = "sender")
    private List<Question> sentQuestions;

    @JsonManagedReference
    @OneToMany(mappedBy = "recipient")
    private List<QuestionAssignment> receivedAssignments;

    @JsonManagedReference
    @OneToMany(mappedBy = "responder")
    private List<Answer> givenAnswers;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Membership> memberships;
}