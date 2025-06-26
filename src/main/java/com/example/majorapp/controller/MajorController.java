package com.example.majorapp.controller;

import com.example.majorapp.dto.CourseDto;
import com.example.majorapp.entity.Major;
import com.example.majorapp.entity.User;
import com.example.majorapp.repository.MajorRepository;
import com.example.majorapp.repository.UserRepository;
import com.example.majorapp.service.MajorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/majors")
public class MajorController {

    private final MajorService majorService;
    private final UserRepository userRepository;
    private final MajorRepository majorRepository;  // ← 추가

    public MajorController(
            MajorService majorService,
            UserRepository userRepository,
            MajorRepository majorRepository  // ← 생성자에도 추가
    ) {
        this.majorService = majorService;
        this.userRepository = userRepository;
        this.majorRepository = majorRepository;
    }

    /**
     * 0) GET /api/majors
     *    → 전체 전공 리스트(id, code, name, description, sortOrder)
     */
    @GetMapping
    public List<Major> listAllMajors() {
        return majorRepository.findAll();
    }

    /**
     * 1) GET /api/majors/courses
     *    → 로그인한 사용자의 전공 과목 리스트
     */
    @GetMapping("/courses")
    public List<CourseDto> listByCurrentUserMajor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Integer majorId = user.getMajor().getId();
        return majorService.getCoursesByMajor(majorId);
    }

    /**
     * 2) GET /api/majors/{majorId}/courses
     *    → 특정 전공ID의 과목 리스트
     */
    @GetMapping("/{majorId}/courses")
    public List<CourseDto> listByMajor(@PathVariable Integer majorId) {
        return majorService.getCoursesByMajor(majorId);
    }
}
