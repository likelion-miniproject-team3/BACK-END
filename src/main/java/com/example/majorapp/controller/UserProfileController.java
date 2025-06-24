package com.example.majorapp.controller;

import com.example.majorapp.dto.UserProfileDto;
import com.example.majorapp.dto.UserProfileUpdateDto;
import com.example.majorapp.entity.User;
import com.example.majorapp.repository.UserRepository;
import com.example.majorapp.service.UserService;
import com.example.majorapp.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/profile")
    public ApiResponse<UserProfileDto> getUserProfile(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return new ApiResponse<>(false, "사용자를 찾을 수 없습니다.", null);
        }

        UserProfileDto profileDto = new UserProfileDto(
                user.getUser_Id(),
                user.getNickname(),
                user.getEmail(),
                user.getStudent_Id(),
                user.getProfilePhotoUrl()
        );

        return new ApiResponse<>(true, "프로필 조회 성공", profileDto);
    }

    @PatchMapping("/{userId}/profile")
    public ResponseEntity<ApiResponse<UserProfileDto>> updateProfile(
            @PathVariable Long userId,
            @RequestBody UserProfileUpdateDto updateDto) {

        UserProfileDto updatedProfile = userService.updateUserProfile(userId, updateDto);
        ApiResponse<UserProfileDto> response = new ApiResponse<>(true, "프로필이 업데이트되었습니다.", updatedProfile);
        return ResponseEntity.ok(response);
    }
}
