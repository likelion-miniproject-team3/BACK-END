package com.example.majorapp.dto;

public class UserProfileDto {
    private Long user_Id;
    private String nickname;
    private String email;
    private String student_Id;
    private String profilePhotoUrl;

    public UserProfileDto(Long user_Id, String nickname, String email, String student_Id, String profilePhotoUrl) {
        this.user_Id = user_Id;
        this.nickname = nickname;
        this.email = email;
        this.student_Id = student_Id;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public Long getUser_Id() {
        return user_Id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getStudent_Id() {
        return student_Id;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }
}
