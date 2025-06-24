// src/main/java/com/example/majorapp/controller/CourseEvaluationController.java
package com.example.majorapp.controller;

import com.example.majorapp.dto.CreateEvaluationRequest;
import com.example.majorapp.dto.EvaluationDto;
import com.example.majorapp.dto.EvaluationSummaryDto;
import com.example.majorapp.service.CourseEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses/{courseId}/evaluations")
public class CourseEvaluationController {

    private final CourseEvaluationService svc;

    public CourseEvaluationController(CourseEvaluationService svc) {
        this.svc = svc;
    }

    /**
     * 평균 평점과 후기 목록을 함께 반환
     */
    @GetMapping
    public ResponseEntity<EvaluationSummaryDto> list(
            @PathVariable Integer courseId
    ) {
        List<EvaluationDto> list = svc.getEvaluationsByCourse(courseId);
        double avg = list.stream()
                .mapToInt(EvaluationDto::rating)
                .average()
                .orElse(0.0);
        return ResponseEntity.ok(new EvaluationSummaryDto(avg, list));
    }

    /**
     * 리뷰 생성 또는 수정 (중복 방지)
     */
    @PostMapping
    public ResponseEntity<EvaluationDto> createOrUpdate(
            @PathVariable Integer courseId,
            @RequestBody CreateEvaluationRequest req
    ) {
        return ResponseEntity.ok(
                svc.createOrUpdateEvaluation(req.userId(), courseId, req)
        );
    }
}
