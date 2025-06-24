package com.example.majorapp.service;

import com.example.majorapp.dto.EnrollmentDto;
import com.example.majorapp.dto.EnrollmentCourseDto;
import com.example.majorapp.entity.Course;
import com.example.majorapp.entity.Enrollment;
import com.example.majorapp.repository.CourseRepository;
import com.example.majorapp.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository repo;
    private final CourseRepository courseRepo;

    public EnrollmentServiceImpl(EnrollmentRepository repo,
                                 CourseRepository courseRepo) {
        this.repo = repo;
        this.courseRepo = courseRepo;
    }

    @Override
    public EnrollmentDto addEnrollment(Long userId, Integer courseId) {
        if (repo.existsByUserIdAndCourseId(userId, courseId)) {
            Enrollment existing = repo.findAllByUserId(userId).stream()
                    .filter(e -> e.getCourseId().equals(courseId))
                    .findFirst()
                    .orElseThrow();
            return new EnrollmentDto(
                    existing.getId(),
                    existing.getUserId(),
                    existing.getCourseId(),
                    existing.getEnrolledAt()
            );
        }
        Enrollment e = new Enrollment(userId, courseId);
        Enrollment saved = repo.save(e);
        return new EnrollmentDto(
                saved.getId(),
                saved.getUserId(),
                saved.getCourseId(),
                saved.getEnrolledAt()
        );
    }

    @Override
    public void removeEnrollment(Long userId, Integer courseId) {
        repo.deleteByUserIdAndCourseId(userId, courseId);
    }

    @Override
    public List<EnrollmentDto> getEnrollments(Long userId) {
        return repo.findAllByUserId(userId).stream()
                .map(e -> new EnrollmentDto(
                        e.getId(),
                        e.getUserId(),
                        e.getCourseId(),
                        e.getEnrolledAt()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentCourseDto> getEnrolledCourses(Long userId) {
        return repo.findAllByUserId(userId).stream()
                .map(e -> {
                    Course c = courseRepo.findById(e.getCourseId())
                            .orElseThrow();
                    return new EnrollmentCourseDto(
                            e.getId(),
                            c.getId(),         // ← 여기서 getId() 로 변경
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
