package com.example.majorapp.dto;

/**
 * 수강후기 작성 요청 DTO
 */
public record CreateEvaluationRequest(
        Long userId,        // 인증 적용 전까지는 요청 바디로 받습니다
        Integer rating,     // 1~5
        Integer semesterYear,
        String semesterTerm,
        String content
) {}
