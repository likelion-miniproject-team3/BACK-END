package com.example.majorapp.dto;

public class UserProfileUpdateRequest {
    private String nickname;
    private String profilePhotoUrl;

    public UserProfileUpdateRequest() {}

    public UserProfileUpdateRequest(String nickname, String profilePhotoUrl) {
        this.nickname = nickname;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }
}
