package com.example.majorapp.repository;

import com.example.majorapp.entity.CourseExam;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CourseExamRepository extends JpaRepository<CourseExam, Long> {
    /**
     * 특정 과목에 대한 모든 유저의 시험정보 조회
     */
    List<CourseExam> findByCourseId(Integer courseId);

    /**
     * 특정 유저의 시험정보 등록/수정 시 기존 레코드 검색용
     */
    Optional<CourseExam> findByUserIdAndCourseIdAndSemesterYearAndSemesterTermAndAttemptType(
            Long userId,
            Integer courseId,
            Integer semesterYear,
            String semesterTerm,
            String attemptType
    );
}