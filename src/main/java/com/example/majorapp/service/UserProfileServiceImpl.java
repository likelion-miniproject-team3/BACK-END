package com.example.majorapp.service;

import com.example.majorapp.dto.CreditSummaryDto;
import com.example.majorapp.dto.ProfileDto;
import com.example.majorapp.entity.User;
import com.example.majorapp.repository.CourseRepository;
import com.example.majorapp.repository.EnrollmentRepository;
import com.example.majorapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {
    private final UserRepository userRepo;
    private final EnrollmentRepository enrollRepo;
    private final CourseRepository courseRepo;
    private static final int REQUIRED_CREDITS = 70;

    public UserProfileServiceImpl(
            UserRepository userRepo,
            EnrollmentRepository enrollRepo,
            CourseRepository courseRepo
    ) {
        this.userRepo = userRepo;
        this.enrollRepo = enrollRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public ProfileDto getMyProfile(String username) {
        User u = findByUsername(username);
        return new ProfileDto(
                u.getUsername(),
                u.getName(),
                u.getNickname(),
                u.getEmail(),
                u.getStudentId(),
                u.getProfilePictureUrl()
        );
    }

    @Override
    public ProfileDto updateNickname(String username, String newNickname) {
        User u = findByUsername(username);
        u.setNickname(newNickname);
        return getMyProfile(username);
    }

    @Override
    public ProfileDto updateProfilePicture(String username, String pictureUrl) {
        User u = findByUsername(username);
        u.setProfilePictureUrl(pictureUrl);
        return getMyProfile(username);
    }

    @Override
    public CreditSummaryDto getCreditSummary(String username) {
        User u = findByUsername(username);

        // 1) 이수한 학점 총합
        int earned = enrollRepo.findAllByUserId(u.getId()).stream()
                .mapToInt(e -> courseRepo.findById(e.getCourseId())
                        .orElseThrow(() ->
                                new IllegalArgumentException("Course not found: " + e.getCourseId()))
                        .getCredit())
                .sum();

        // 2) 남은 학점
        int remaining = Math.max(0, REQUIRED_CREDITS - earned);

        // 3) 진행 퍼센트 계산 (소수점 한 자리로 반올림)
        double percent = (earned / (double) REQUIRED_CREDITS) * 100;
        double progressPercent = Math.round(percent * 10) / 10.0;

        return new CreditSummaryDto(earned, remaining, REQUIRED_CREDITS, progressPercent);
    }

    private User findByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
    }
}
