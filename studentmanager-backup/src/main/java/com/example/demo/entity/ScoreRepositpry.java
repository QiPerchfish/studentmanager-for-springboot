package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Score")
@Data
public class ScoreRepositpry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "score_value",precision = 5,scale = 2)
    private BigDecimal scoreValue;

    @Column(name = "create_at",updatable = false)
    private LocalDateTime createAt;

    @Column(name = "exam_type")
    private String examType;

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
    }
}
