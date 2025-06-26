package com.example.majorapp.dto;

public record ProfileDto(
        String username,
        String name,
        String nickname,
        String email,
        String studentId,
        String profilePictureUrl
) {}