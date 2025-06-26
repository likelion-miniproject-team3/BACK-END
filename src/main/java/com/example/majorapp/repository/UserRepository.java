package com.example.majorapp.repository;

import com.example.majorapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    // 이메일·별명·학번 중복 체크
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    boolean existsByStudentId(String studentId);
}
