package com.example.majorapp.dto;

public class UserProfileUpdateDto {
    private String nickname;
    private String profilePhotoUrl;

    public UserProfileUpdateDto() {}

    public UserProfileUpdateDto(String nickname, String profilePhotoUrl) {
        this.nickname = nickname;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }
}
