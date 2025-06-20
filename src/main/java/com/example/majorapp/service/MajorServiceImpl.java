package com.example.majorapp.service;

import com.example.majorapp.dto.CourseDto;
import com.example.majorapp.entity.Major;
import com.example.majorapp.repository.MajorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MajorServiceImpl implements MajorService {

    private final MajorRepository majorRepository;

    public MajorServiceImpl(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    @Override
    public List<CourseDto> getCoursesByMajor(Integer majorId) {
        Major major = majorRepository.findById(majorId)
                .orElseThrow(() -> new EntityNotFoundException("Major not found: " + majorId));

        return major.getCourses().stream()
                .map(c -> new CourseDto(
                        c.getId(),
                        c.getCode(),
                        c.getName(),
                        c.getDescription(),
                        c.getYear(),
                        c.getSemester(),
                        c.getCredit(),
                        c.getRequired()
                ))
                .collect(Collectors.toList());
    }
}
