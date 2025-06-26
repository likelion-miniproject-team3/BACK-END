package com.example.majorapp.dto;

/**
 * 이수학점 현황 DTO
 */
public record CreditSummaryDto(
        int earnedCredits,      // 이수한 학점
        int remainingCredits,   // 남은 학점
        int requiredCredits,    // 총 필요 학점
        double progressPercent  // 진행 퍼센트 (소수점 한 자리)
) {}
