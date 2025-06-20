package com.example.majorapp.controller;

import com.example.majorapp.dto.CourseDto;
import com.example.majorapp.service.MajorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/majors")
public class MajorController {

    private final MajorService majorService;

    public MajorController(MajorService majorService) {
        this.majorService = majorService;
    }

    @GetMapping("/{majorId}/courses")
    public List<CourseDto> listByMajor(@PathVariable Integer majorId) {
        return majorService.getCoursesByMajor(majorId);
    }
}
