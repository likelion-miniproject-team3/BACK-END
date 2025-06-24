// src/main/java/com/example/majorapp/service/CourseEvaluationService.java
package com.example.majorapp.service;

import com.example.majorapp.dto.CreateEvaluationRequest;
import com.example.majorapp.dto.EvaluationDto;

import java.util.List;

public interface CourseEvaluationService {

    /** 특정 과목의 수강후기를 모두 조회 */
    List<EvaluationDto> getEvaluationsByCourse(Integer courseId);

    /**
     * 리뷰 생성 또는 업데이트
     * - 이미 있으면 수정, 없으면 신규 생성
     */
    EvaluationDto createOrUpdateEvaluation(
            Long userId,
            Integer courseId,
            CreateEvaluationRequest req
    );
}
