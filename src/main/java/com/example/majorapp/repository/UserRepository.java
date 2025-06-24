package com.example.majorapp.repository;

import com.example.majorapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 닉네임 조회나 인증 시 사용할 수 있도록
     * Optional<User> findByUsername(String username);
     * 같은 메서드를 추가로 선언해도 좋습니다.
     */
    Optional<User> findById(Long id);
}
