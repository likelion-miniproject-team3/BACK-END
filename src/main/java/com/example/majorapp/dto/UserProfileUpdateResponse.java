package com.example.majorapp.dto;

public class UserProfileUpdateResponse {
    private Long userId;
    private String nickname;
    private String profilePhotoUrl;

    public UserProfileUpdateResponse() {}

    public UserProfileUpdateResponse(Long userId, String nickname, String profilePhotoUrl) {
        this.userId = userId;
        this.nickname = nickname;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }
}
