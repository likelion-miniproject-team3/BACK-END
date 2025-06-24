package com.example.majorapp.dto;

public record CreateExamRequest(
        Long    userId,
        Integer semesterYear,
        String  semesterTerm,
        String  attemptType,
        String  content
) {}