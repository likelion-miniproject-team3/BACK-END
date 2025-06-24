// src/main/java/com/example/majorapp/dto/EvaluationSummaryDto.java
package com.example.majorapp.dto;

import java.util.List;

/**
 * 전체 후기를 묶어서 평균 평점과 함께 반환할 때 쓰는 DTO
 */
public record EvaluationSummaryDto(
        double averageRating,
        List<EvaluationDto> evaluations
) {}
