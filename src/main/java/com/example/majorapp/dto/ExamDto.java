package com.example.majorapp.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ExamDto(
        Long       examId,
        Long       userId,
        String     nickname,
        Integer    courseId,
        Integer    semesterYear,
        String     semesterTerm,
        String     attemptType,
        String     content,
        List<String> fileUrls,
        LocalDateTime createdAt
) {}