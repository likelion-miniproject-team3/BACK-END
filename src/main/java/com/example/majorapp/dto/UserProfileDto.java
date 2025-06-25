package com.example.majorapp.dto;

public class UserProfileDto {
    private Long userId;
    private String nickname;
    private String email;
    private String studentId;
    private String profilePhotoUrl;

    public UserProfileDto() {}

    public UserProfileDto(Long userId, String nickname, String email, String studentId, String profilePhotoUrl) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.studentId = studentId;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }
}
