package com.example.majorapp.controller;

import com.example.majorapp.dto.EnrollmentDto;
import com.example.majorapp.dto.EnrollmentRequest;
import com.example.majorapp.dto.EnrollmentCourseDto;
import com.example.majorapp.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService svc;

    public EnrollmentController(EnrollmentService svc) {
        this.svc = svc;
    }

    /** 1) 내 수강내역에 과목 추가 */
    @PostMapping
    public ResponseEntity<EnrollmentDto> addEnrollment(
            @RequestBody EnrollmentRequest req,
            Authentication auth
    ) {
        String username = auth.getName();
        EnrollmentDto dto = svc.addEnrollment(username, req.courseId());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    /** 2) 내 수강내역 ID 리스트 조회 */
    @GetMapping
    public ResponseEntity<List<EnrollmentDto>> listEnrollments(
            Authentication auth
    ) {
        String username = auth.getName();
        return ResponseEntity.ok(svc.getEnrollments(username));
    }

    /** 3) 내 수강내역에서 과목 제거 */
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> removeEnrollment(
            @PathVariable Integer courseId,
            Authentication auth
    ) {
        String username = auth.getName();
        svc.removeEnrollment(username, courseId);
        return ResponseEntity.ok().build();
    }

    /** 4) 내 수강내역 + 과목정보 조회 */
    @GetMapping("/courses")
    public ResponseEntity<List<EnrollmentCourseDto>> listEnrolledCourses(
            Authentication auth
    ) {
        String username = auth.getName();
        return ResponseEntity.ok(svc.getEnrolledCourses(username));
    }
}
