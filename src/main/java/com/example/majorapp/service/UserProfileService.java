package com.example.majorapp.service;

import com.example.majorapp.dto.CreditSummaryDto;
import com.example.majorapp.dto.ProfileDto;

public interface UserProfileService {
    ProfileDto getMyProfile(String username);
    ProfileDto updateNickname(String username, String newNickname);
    ProfileDto updateProfilePicture(String username, String pictureUrl);
    CreditSummaryDto getCreditSummary(String username);
}