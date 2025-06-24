package com.example.majorapp.service;

import com.example.majorapp.dto.EnrollmentDto;
import com.example.majorapp.dto.EnrollmentCourseDto;

import java.util.List;

public interface EnrollmentService {
    EnrollmentDto addEnrollment(Long userId, Integer courseId);
    void removeEnrollment(Long userId, Integer courseId);
    List<EnrollmentDto> getEnrollments(Long userId);

    // ← 여기를 추가
    List<EnrollmentCourseDto> getEnrolledCourses(Long userId);
}
