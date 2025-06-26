package com.example.majorapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.majorapp.entity.RegistrationSession;

public interface RegistrationSessionRepository extends JpaRepository<RegistrationSession, String> { }
