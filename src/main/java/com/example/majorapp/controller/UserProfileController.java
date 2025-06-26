package com.example.majorapp.controller;

import com.example.majorapp.dto.CreditSummaryDto;
import com.example.majorapp.dto.ProfileDto;
import com.example.majorapp.dto.UpdateNicknameRequest;
import com.example.majorapp.service.FileStorageService;
import com.example.majorapp.service.UserProfileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/users/me")
public class UserProfileController {
    private final UserProfileService svc;
    private final FileStorageService storage;

    public UserProfileController(
            UserProfileService svc,
            FileStorageService storage
    ) {
        this.svc = svc;
        this.storage = storage;
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getProfile(Authentication auth) {
        return ResponseEntity.ok(
                svc.getMyProfile(auth.getName())
        );
    }

    @PutMapping("/profile/nickname")
    public ResponseEntity<ProfileDto> updateNickname(
            Authentication auth,
            @RequestBody UpdateNicknameRequest req
    ) {
        return ResponseEntity.ok(
                svc.updateNickname(auth.getName(), req.nickname())
        );
    }

    /** 프로필 사진 업로드 */
    @PostMapping(
            value = "/profile/picture",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProfileDto> uploadPicture(
            Authentication auth,
            @RequestPart MultipartFile file
    ) throws IOException {
        // 단일 파일 업로드: storeAll 사용
        java.util.List<String> urls = storage.storeAll(java.util.List.of(file));
        String url = urls.isEmpty() ? null : urls.get(0);
        return ResponseEntity.ok(
                svc.updateProfilePicture(auth.getName(), url)
        );
    }
    @GetMapping("/credits")
    public ResponseEntity<CreditSummaryDto> getCredits(Authentication auth) {
        return ResponseEntity.ok(
                svc.getCreditSummary(auth.getName())
        );
    }
}