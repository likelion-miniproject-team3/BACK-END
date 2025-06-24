package com.example.majorapp.repository;

import com.example.majorapp.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findAllByUserId(Long userId);
    void deleteByUserIdAndCourseId(Long userId, Integer courseId);

    // 중복 추가 방지를 위한 메서드
    boolean existsByUserIdAndCourseId(Long userId, Integer courseId);
}
