package com.example.majorapp.dto;

public record CourseDto(
        Integer id,
        String code,
        String name,
        String description,
        Integer year,
        Integer semester,
        Integer credit,
        Boolean required
) {}
