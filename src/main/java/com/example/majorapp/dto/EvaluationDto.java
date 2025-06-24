// src/main/java/com/example/majorapp/dto/EvaluationDto.java
package com.example.majorapp.dto;

import java.time.LocalDateTime;

/**
 * 개별 후기 항목
 */
public record EvaluationDto(
        Long evaluationId,
        Long userId,
        String nickname,
        Integer courseId,
        Integer rating,
        Integer semesterYear,
        String semesterTerm,
        String content,
        LocalDateTime createdAt
) {}
