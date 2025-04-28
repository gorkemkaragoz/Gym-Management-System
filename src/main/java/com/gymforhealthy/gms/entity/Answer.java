package com.gymforhealthy.gms.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "response", nullable = false)
    private String response;

    @Column(name = "responded_at")
    private LocalDateTime respondedAt;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference
    private Question question;

    @ManyToOne
    @JoinColumn(name = "responder_id", nullable = false)
    @JsonBackReference
    private User responder;
}