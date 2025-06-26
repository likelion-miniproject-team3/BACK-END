package com.example.majorapp.service;

import com.example.majorapp.dto.*;

public interface AuthService {
    String registerStep1(RegisterStep1Request req);
    void registerStep2(RegisterStep2Request req);
    void registerStep3(RegisterStep3Request req);
    void registerStep4(RegisterStep4Request req);
    String login(LoginRequest req);
}
