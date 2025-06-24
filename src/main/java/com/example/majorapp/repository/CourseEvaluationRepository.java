// src/main/java/com/example/majorapp/repository/CourseEvaluationRepository.java
package com.example.majorapp.repository;

import com.example.majorapp.entity.CourseEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseEvaluationRepository
        extends JpaRepository<CourseEvaluation, Long> {

    /** 특정 과목의 모든 리뷰 조회 */
    List<CourseEvaluation> findAllByCourseId(Integer courseId);

    /** 한 사용자가 해당 과목에 리뷰를 남겼는지 체크 */
    boolean existsByUserIdAndCourseId(Long userId, Integer courseId);

    /** 한 사용자가 해당 과목에 남긴 리뷰 하나 조회 */
    Optional<CourseEvaluation> findByUserIdAndCourseId(Long userId, Integer courseId);
}
