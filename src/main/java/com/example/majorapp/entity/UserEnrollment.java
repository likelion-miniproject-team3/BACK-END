package com.example.majorapp.entity;

import jakarta.persistence.*;

@Entity
public class UserEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Course getCourse() {
        return course;
    }

    // 기타 getter, setter, 생성자 생략
}
