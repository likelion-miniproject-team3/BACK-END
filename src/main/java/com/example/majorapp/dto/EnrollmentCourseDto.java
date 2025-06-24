package com.example.majorapp.dto;

import java.time.LocalDateTime;

public record EnrollmentCourseDto(
        Long enrollmentId,
        Integer courseId,
        String code,
        String name,
        String description,
        Integer year,
        Integer semester,
        Integer credit,
        LocalDateTime enrolledAt
) {}
