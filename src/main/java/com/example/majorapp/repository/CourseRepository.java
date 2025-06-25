package com.example.majorapp.repository;

import com.example.majorapp.entity.Course;  // 수정된 import 경로
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    // 필요하면 추가 메서드 작성
}
