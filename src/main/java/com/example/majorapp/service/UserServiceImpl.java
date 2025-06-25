package com.example.majorapp.service;

import com.example.majorapp.dto.CourseProgressDto;
import com.example.majorapp.dto.UserProfileDto;
import com.example.majorapp.dto.UserProfileUpdateDto;
import com.example.majorapp.entity.User;
import com.example.majorapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserProfileDto getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return new UserProfileDto(
                user.getId(),
                user.getNickname(),
                user.getEmail(),
                user.getStudentId(),
                user.getProfilePhotoUrl()
        );
    }

    @Override
    public UserProfileDto updateUserProfile(Long userId, UserProfileUpdateDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        user.setNickname(dto.getNickname());
        user.setProfilePhotoUrl(dto.getProfilePhotoUrl());

        User updatedUser = userRepository.save(user);

        return new UserProfileDto(
                updatedUser.getId(),
                updatedUser.getNickname(),
                updatedUser.getEmail(),
                updatedUser.getStudentId(),
                updatedUser.getProfilePhotoUrl()
        );
    }

    @Override
    public CourseProgressDto getCourseProgress(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        int requiredCredits = 70;
        int earnedCredits = 46;

        int remainingCredits = requiredCredits - earnedCredits;
        double progressPercent = (earnedCredits * 100.0) / requiredCredits;

        return new CourseProgressDto(
                user.getMajorId().longValue(),
                requiredCredits,
                earnedCredits,
                remainingCredits,
                progressPercent
        );
    }
}
