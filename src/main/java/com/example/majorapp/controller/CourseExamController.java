package com.example.majorapp.controller;

import com.example.majorapp.dto.CreateExamRequest;
import com.example.majorapp.dto.ExamDto;
import com.example.majorapp.service.ExamService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    /**
     * GET: 특정 과목에 작성된 모든 유저의 시험정보
     */
    @GetMapping
    public ResponseEntity<List<ExamDto>> list(@PathVariable Integer courseId) {
        return ResponseEntity.ok(svc.getExams(courseId));
    }

    /**
     * POST: 특정 유저의 시험정보 등록/수정
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ExamDto> createOrUpdate(
            @PathVariable Integer courseId,
            @RequestParam Long userId,
            @RequestParam Integer semesterYear,
            @RequestParam String semesterTerm,
            @RequestParam String attemptType,
            @RequestParam String content,
            @RequestPart(required = false) List<MultipartFile> files
    ) throws IOException {
        CreateExamRequest req = new CreateExamRequest(
                userId,
                semesterYear,
                semesterTerm,
                attemptType,
                content
        );
        return ResponseEntity.ok(
                svc.createOrUpdateExam(courseId, req, files)
        );
    }
}