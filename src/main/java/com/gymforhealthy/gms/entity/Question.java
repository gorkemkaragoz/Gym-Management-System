package com.gymforhealthy.gms.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_type", nullable = false)
    private SubjectType subjectType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.OPEN;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    @JsonBackReference
    private User sender;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Answer> answers;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<QuestionAssignment> assignments;


    public enum SubjectType {
        SUGGESTION,
        COMPLAINT,
        QUESTION
    }

    public enum Status {
        OPEN,
        IN_PROGRESS,
        RESOLVED
    }
}