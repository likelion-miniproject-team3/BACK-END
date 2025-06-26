package com.example.majorapp.service;

import com.example.majorapp.dto.EnrollmentDto;
import com.example.majorapp.dto.EnrollmentCourseDto;

import java.util.List;

public interface EnrollmentService {
    EnrollmentDto addEnrollment(String username, Integer courseId);
    void removeEnrollment(String username, Integer courseId);
    List<EnrollmentDto> getEnrollments(String username);
    List<EnrollmentCourseDto> getEnrolledCourses(String username);
}
