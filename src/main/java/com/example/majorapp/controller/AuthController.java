package com.example.majorapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.majorapp.dto.*;
import com.example.majorapp.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // 1단계: 이름/닉네임/이메일/학번 → tempId 반환
    @PostMapping("/register/step1")
    public String registerStep1(@RequestBody RegisterStep1Request req) {
        return authService.registerStep1(req);
    }

    // 2단계: tempId + username
    @PostMapping("/register/step2")
    public void registerStep2(@RequestBody RegisterStep2Request req) {
        authService.registerStep2(req);
    }

    // 3단계: tempId + password + confirmPassword
    @PostMapping("/register/step3")
    public void registerStep3(@RequestBody RegisterStep3Request req) {
        authService.registerStep3(req);
    }

    // 4단계: tempId + majorId → 회원 생성 완료
    @PostMapping("/register/step4")
    public void registerStep4(@RequestBody RegisterStep4Request req) {
        authService.registerStep4(req);
    }

    // 로그인
    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest req) {
        String token = authService.login(req);
        return new JwtResponse(token);
    }
}
