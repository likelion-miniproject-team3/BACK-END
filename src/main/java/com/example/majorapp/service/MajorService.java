package com.example.majorapp.service;

import com.example.majorapp.dto.CourseDto;

import java.util.List;

public interface MajorService {
    List<CourseDto> getCoursesByUserId(Long userId);
}
