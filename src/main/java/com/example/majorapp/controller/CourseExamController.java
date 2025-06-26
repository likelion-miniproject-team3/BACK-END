package com.example.majorapp.controller;

import com.example.majorapp.dto.CreateExamRequest;
import com.example.majorapp.dto.ExamDto;
import com.example.majorapp.service.ExamService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/courses/{courseId}/exams")
public class CourseExamController {

    private final ExamService svc;

    public CourseExamController(ExamService svc) {
        this.svc = svc;
    }

    /** 모든 사용자 시험정보 조회 (토큰 필수) */
    @GetMapping
    public ResponseEntity<List<ExamDto>> list(
            @PathVariable Integer courseId,
            Authentication auth
    ) {
        // 조회도 회원제이므로 auth 검증만 됐으면 OK
        return ResponseEntity.ok(svc.getExams(courseId));
    }

    /** 로그인 유저의 시험정보 생성/수정 */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ExamDto> createOrUpdate(
            @PathVariable Integer courseId,
            @RequestParam Integer semesterYear,
            @RequestParam String semesterTerm,
            @RequestParam String attemptType,
            @RequestParam String content,
            @RequestPart(required = false) List<MultipartFile> files,
            Authentication auth
    ) throws IOException {
        String username = auth.getName();
        CreateExamRequest req = new CreateExamRequest(
                semesterYear,
                semesterTerm,
                attemptType,
                content
        );
        return ResponseEntity.ok(
                svc.createOrUpdateExam(username, courseId, req, files)
        );
    }
}
