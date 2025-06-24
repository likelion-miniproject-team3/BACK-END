package com.example.majorapp.controller;

import com.example.majorapp.dto.EnrollmentDto;
import com.example.majorapp.dto.EnrollmentRequest;
import com.example.majorapp.dto.EnrollmentCourseDto;
import com.example.majorapp.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/enrollments")
public class EnrollmentController {
    private final EnrollmentService svc;

    public EnrollmentController(EnrollmentService svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<EnrollmentDto> add(
            @PathVariable Long userId,
            @RequestBody EnrollmentRequest req
    ) {
        return ResponseEntity.ok(svc.addEnrollment(userId, req.courseId()));
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDto>> list(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(svc.getEnrollments(userId));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long userId,
            @PathVariable Integer courseId
    ) {
        svc.removeEnrollment(userId, courseId);
        return ResponseEntity.ok().build();
    }

    // ← 추가된 엔드포인트: 수강내역 + 과목정보
    @GetMapping("/courses")
    public ResponseEntity<List<EnrollmentCourseDto>> listWithCourseInfo(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(svc.getEnrolledCourses(userId));
    }
}
