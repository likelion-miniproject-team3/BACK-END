package com.example.majorapp.repository;

import com.example.majorapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 필요시 커스텀 쿼리 메서드를 추가
}
