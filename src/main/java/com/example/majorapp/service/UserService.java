package com.example.majorapp.service;

import com.example.majorapp.dto.UserProfileDto;
import com.example.majorapp.dto.UserProfileUpdateDto;

public interface UserService {
    UserProfileDto updateUserProfile(Long userId, UserProfileUpdateDto updateDto);
}

