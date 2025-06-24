package com.example.majorapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "course_exams",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_id","course_id","semester_year","semester_term","attempt_type"}
        )
)
public class CourseExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long examId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "semester_year", nullable = false)
    private Integer semesterYear;

    @Column(name = "semester_term", nullable = false, length = 10)
    private String semesterTerm;

    @Column(name = "attempt_type", nullable = false, length = 20)
    private String attemptType;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ElementCollection
    @CollectionTable(
            name = "course_exam_files",
            joinColumns = @JoinColumn(name = "exam_id")
    )
    @Column(name = "file_url", length = 500)
    private List<String> fileUrls = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected CourseExam() {}

    public CourseExam(
            Long userId,
            Integer courseId,
            Integer semesterYear,
            String semesterTerm,
            String attemptType,
            String content,
            List<String> fileUrls
    ) {
        this.userId       = userId;
        this.courseId     = courseId;
        this.semesterYear = semesterYear;
        this.semesterTerm = semesterTerm;
        this.attemptType  = attemptType;
        this.content      = content;
        if (fileUrls != null) this.fileUrls.addAll(fileUrls);
        this.createdAt    = LocalDateTime.now();
    }

    // getters
    public Long getExamId()       { return examId; }
    public Long getUserId()       { return userId; }
    public Integer getCourseId()  { return courseId; }
    public Integer getSemesterYear() { return semesterYear; }
    public String getSemesterTerm()  { return semesterTerm; }
    public String getAttemptType()   { return attemptType; }
    public String getContent()       { return content; }
    public List<String> getFileUrls(){ return fileUrls; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // setters for updates
    public void setContent(String content)               { this.content = content; }
    public void setSemesterYear(Integer semesterYear)    { this.semesterYear = semesterYear; }
    public void setSemesterTerm(String semesterTerm)     { this.semesterTerm = semesterTerm; }
    public void setAttemptType(String attemptType)       { this.attemptType = attemptType; }
    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls.clear();
        if (fileUrls != null) this.fileUrls.addAll(fileUrls);
    }
}