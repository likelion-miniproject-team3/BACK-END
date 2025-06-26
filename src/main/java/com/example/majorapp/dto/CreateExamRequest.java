package com.example.majorapp.dto;

public record CreateExamRequest(
        Integer semesterYear,
        String semesterTerm,
        String attemptType,
        String content
) {}
