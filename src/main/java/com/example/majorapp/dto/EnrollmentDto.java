package com.example.majorapp.dto;

import java.time.LocalDateTime;

public record EnrollmentDto(
        Long enrollmentId,
        Long userId,
        Integer courseId,
        LocalDateTime enrolledAt
) {}
