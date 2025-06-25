package com.example.majorapp.repository;

import com.example.majorapp.entity.UserEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserEnrollmentRepository extends JpaRepository<UserEnrollment, Long> {
    List<UserEnrollment> findByUserId(Long userId);
}
