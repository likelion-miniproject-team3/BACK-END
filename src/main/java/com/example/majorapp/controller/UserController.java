package com.example.majorapp.controller;

import com.example.majorapp.dto.ApiResponse;
import com.example.majorapp.dto.CourseProgressDto;
import com.example.majorapp.dto.UserProfileDto;
import com.example.majorapp.dto.UserProfileUpdateDto;
import com.example.majorapp.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/profile")
    public ApiResponse<UserProfileDto> getUserProfile(@PathVariable Long userId) {
        UserProfileDto dto = userService.getUserProfile(userId);
        return new ApiResponse<>(true, "프로필 조회 성공", dto);
    }

    @PatchMapping("/{userId}/profile")
    public ApiResponse<UserProfileDto> updateUserProfile(
            @PathVariable Long userId,
            @RequestBody UserProfileUpdateDto updateDto
    ) {
        UserProfileDto dto = userService.updateUserProfile(userId, updateDto);
        return new ApiResponse<>(true, "프로필이 업데이트되었습니다.", dto);
    }

    @GetMapping("/{userId}/progress")
    public ApiResponse<CourseProgressDto> getCourseProgress(@PathVariable Long userId) {
        CourseProgressDto progress = userService.getCourseProgress(userId);
        return new ApiResponse<>(true, "학점 진행도 조회 성공", progress);
    }
}
