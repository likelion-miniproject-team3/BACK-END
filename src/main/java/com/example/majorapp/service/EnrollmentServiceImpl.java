package com.example.majorapp.service;

import com.example.majorapp.dto.EnrollmentDto;
import com.example.majorapp.dto.EnrollmentCourseDto;
import com.example.majorapp.entity.Course;
import com.example.majorapp.entity.Enrollment;
import com.example.majorapp.entity.User;
import com.example.majorapp.repository.CourseRepository;
import com.example.majorapp.repository.EnrollmentRepository;
import com.example.majorapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository repo;
    private final CourseRepository courseRepo;
    private final UserRepository userRepo;

    public EnrollmentServiceImpl(EnrollmentRepository repo,
                                 CourseRepository courseRepo,
                                 UserRepository userRepo) {
        this.repo = repo;
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
    }

    @Override
    public EnrollmentDto addEnrollment(String username, Integer courseId) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Long uid = user.getId();
        // 이미 있으면 기존 객체 반환
        if (repo.existsByUserIdAndCourseId(uid, courseId)) {
            Enrollment e = repo.findAllByUserId(uid).stream()
                    .filter(x -> x.getCourseId().equals(courseId))
                    .findFirst()
                    .orElseThrow();
            return new EnrollmentDto(
                    e.getId(),
                    e.getUserId(),
                    e.getCourseId(),
                    e.getEnrolledAt()
            );
        }
        Enrollment en = new Enrollment(uid, courseId);
        Enrollment saved = repo.save(en);
        return new EnrollmentDto(
                saved.getId(),
                saved.getUserId(),
                saved.getCourseId(),
                saved.getEnrolledAt()
        );
    }

    @Override
    public void removeEnrollment(String username, Integer courseId) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        repo.deleteByUserIdAndCourseId(user.getId(), courseId);
    }

    @Override
    public List<EnrollmentDto> getEnrollments(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return repo.findAllByUserId(user.getId()).stream()
                .map(e -> new EnrollmentDto(
                        e.getId(),
                        e.getUserId(),
                        e.getCourseId(),
                        e.getEnrolledAt()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentCourseDto> getEnrolledCourses(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return repo.findAllByUserId(user.getId()).stream()
                .map(e -> {
                    Course c = courseRepo.findById(e.getCourseId())
                            .orElseThrow(() -> new IllegalArgumentException("Course not found"));
                    return new EnrollmentCourseDto(
                            e.getId(),
                            c.getId(),
                            c.getCode(),
                            c.getName(),
                            c.getDescription(),
                            c.getYear(),
                            c.getSemester(),
                            c.getCredit(),
                            e.getEnrolledAt()
                    );
                })
                .collect(Collectors.toList());
    }
}
