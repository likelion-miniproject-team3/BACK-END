package com.example.majorapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "course_evaluations")
public class CourseEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id")
    private Long id;

    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer rating;  // 1~5

    @Column(name = "semester_year", nullable = false)
    private Integer semesterYear;

    @Column(name = "semester_term", length = 10, nullable = false)
    private String semesterTerm;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    public CourseEvaluation() {}

    public CourseEvaluation(Long userId,
                            Integer courseId,
                            Integer rating,
                            Integer semesterYear,
                            String semesterTerm,
                            String content) {
        this.userId       = userId;
        this.courseId     = courseId;
        this.rating       = rating;
        this.semesterYear = semesterYear;
        this.semesterTerm = semesterTerm;
        this.content      = content;
    }

    // --- getters & setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public Integer getSemesterYear() { return semesterYear; }
    public void setSemesterYear(Integer semesterYear) {
        this.semesterYear = semesterYear;
    }

    public String getSemesterTerm() { return semesterTerm; }
    public void setSemesterTerm(String semesterTerm) {
        this.semesterTerm = semesterTerm;
    }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
