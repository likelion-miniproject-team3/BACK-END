package com.example.majorapp.dto;

/**
 * 수강후기 작성 요청 DTO
 * Authentication 으로부터 userId 를 꺼내기 때문에
 * 클라이언트에는 userId 를 받지 않습니다.
 */
public record CreateEvaluationRequest(
        Integer rating,       // 1~5
        Integer semesterYear, // 이수 연도
        String semesterTerm,  // 이수 학기 (예: "1학기")
        String content        // 후기 내용
) {}
