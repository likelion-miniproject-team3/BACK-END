package com.example.majorapp.service;

import com.example.majorapp.dto.CourseProgressDto;
import com.example.majorapp.dto.UserProfileDto;
import com.example.majorapp.dto.UserProfileUpdateDto;

public interface UserService {
    UserProfileDto getUserProfile(Long userId);
    UserProfileDto updateUserProfile(Long userId, UserProfileUpdateDto dto);
    CourseProgressDto getCourseProgress(Long userId);
}
