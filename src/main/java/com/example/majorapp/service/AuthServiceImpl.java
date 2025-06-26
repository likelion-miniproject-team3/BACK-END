package com.example.majorapp.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.majorapp.dto.LoginRequest;
import com.example.majorapp.dto.RegisterStep1Request;
import com.example.majorapp.dto.RegisterStep2Request;
import com.example.majorapp.dto.RegisterStep3Request;
import com.example.majorapp.dto.RegisterStep4Request;
import com.example.majorapp.entity.Major;
import com.example.majorapp.entity.RegistrationSession;
import com.example.majorapp.entity.User;
import com.example.majorapp.repository.MajorRepository;
import com.example.majorapp.repository.RegistrationSessionRepository;
import com.example.majorapp.repository.UserRepository;
import com.example.majorapp.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

    private final RegistrationSessionRepository sessionRepo;
    private final UserRepository userRepo;
    private final MajorRepository majorRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(
            RegistrationSessionRepository sessionRepo,
            UserRepository userRepo,
            MajorRepository majorRepo,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager
    ) {
        this.sessionRepo = sessionRepo;
        this.userRepo = userRepo;
        this.majorRepo = majorRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String registerStep1(RegisterStep1Request req) {
        // 1단계: 이메일, 별명, 학번 중복 검사
        if (userRepo.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        if (userRepo.existsByNickname(req.getNickname())) {
            throw new IllegalArgumentException("이미 사용 중인 별명입니다.");
        }
        if (userRepo.existsByStudentId(req.getStudentId())) {
            throw new IllegalArgumentException("이미 사용 중인 학번입니다.");
        }

        // 임시 세션 생성
        String tempId = UUID.randomUUID().toString();
        RegistrationSession s = new RegistrationSession();
        s.setTempId(tempId);
        s.setName(req.getName());
        s.setEmail(req.getEmail());
        s.setNickname(req.getNickname());
        s.setStudentId(req.getStudentId());
        sessionRepo.save(s);
        return tempId;
    }

    @Override
    public void registerStep2(RegisterStep2Request req) {
        if (userRepo.existsByUsername(req.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        RegistrationSession s = sessionRepo.findById(req.getTempId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid tempId"));
        s.setUsername(req.getUsername());
        sessionRepo.save(s);
    }

    @Override
    public void registerStep3(RegisterStep3Request req) {
        if (!req.getPassword().equals(req.getConfirmPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        RegistrationSession s = sessionRepo.findById(req.getTempId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid tempId"));
        s.setPassword(passwordEncoder.encode(req.getPassword()));
        sessionRepo.save(s);
    }

    @Override
    public void registerStep4(RegisterStep4Request req) {
        RegistrationSession s = sessionRepo.findById(req.getTempId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid tempId"));
        if (userRepo.existsByUsername(s.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        Major major = majorRepo.findById(req.getMajorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid majorId"));
        User u = new User();
        u.setName(s.getName());
        u.setEmail(s.getEmail());
        u.setNickname(s.getNickname());
        u.setStudentId(s.getStudentId());
        u.setUsername(s.getUsername());
        u.setPassword(s.getPassword());
        u.setMajor(major);
        userRepo.save(u);
        sessionRepo.delete(s);
    }

    @Override
    public String login(LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        return jwtUtil.generateToken(req.getUsername());
    }
}
