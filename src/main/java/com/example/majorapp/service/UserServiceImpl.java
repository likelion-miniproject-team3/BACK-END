package com.example.majorapp.service;

import com.example.majorapp.dto.UserProfileDto;
import com.example.majorapp.dto.UserProfileUpdateDto;
import com.example.majorapp.entity.User;
import com.example.majorapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserProfileDto updateUserProfile(Long userId, UserProfileUpdateDto updateDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 필요한 필드들만 업데이트 (예: 닉네임, 프로필 사진)
        user.setNickname(updateDto.getNickname());
        user.setProfilePhotoUrl(updateDto.getProfilePhotoUrl());

        // 저장
        userRepository.save(user);

        // 업데이트 후 DTO 반환
        return new UserProfileDto(
                user.getUser_Id(),
                user.getNickname(),
                user.getEmail(),
                user.getStudent_Id(),
                user.getProfilePhotoUrl()
        );
    }
}
