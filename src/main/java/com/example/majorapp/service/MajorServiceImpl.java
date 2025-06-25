package com.example.majorapp.service;

import com.example.majorapp.dto.CourseDto;
import com.example.majorapp.entity.Course;
import com.example.majorapp.entity.Enrollment;
import com.example.majorapp.repository.EnrollmentRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MajorServiceImpl implements MajorService {

    private final EnrollmentRepository enrollmentRepository;

    public MajorServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public List<CourseDto> getCoursesByUserId(Long userId) {
        List<Enrollment> enrollments = enrollmentRepository.findByUserId(userId);

        return enrollments.stream()
                .map(enrollment -> {
                    Course course = enrollment.getCourse();
                    return new CourseDto(course.getId(), course.getName(), course.getCredit());
                })
                .collect(Collectors.toList());
    }
}
