// src/main/java/com/example/majorapp/service/CourseEvaluationServiceImpl.java
package com.example.majorapp.service;

import com.example.majorapp.dto.CreateEvaluationRequest;
import com.example.majorapp.dto.EvaluationDto;
import com.example.majorapp.entity.CourseEvaluation;
import com.example.majorapp.entity.User;
import com.example.majorapp.repository.CourseEvaluationRepository;
import com.example.majorapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseEvaluationServiceImpl implements CourseEvaluationService {

    private final CourseEvaluationRepository evalRepo;
    private final UserRepository            userRepo;

    public CourseEvaluationServiceImpl(
            CourseEvaluationRepository evalRepo,
            UserRepository userRepo
    ) {
        this.evalRepo = evalRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<EvaluationDto> getEvaluationsByCourse(Integer courseId) {
        return evalRepo.findAllByCourseId(courseId).stream()
                .map(e -> {
                    User u = userRepo.findById(e.getUserId())
                            .orElseThrow();
                    return new EvaluationDto(
                            e.getId(),
                            e.getUserId(),
                            u.getNickname(),
                            e.getCourseId(),
                            e.getRating(),
                            e.getSemesterYear(),
                            e.getSemesterTerm(),
                            e.getContent(),
                            e.getCreatedAt()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public EvaluationDto createOrUpdateEvaluation(
            Long userId,
            Integer courseId,
            CreateEvaluationRequest req
    ) {
        if (evalRepo.existsByUserIdAndCourseId(userId, courseId)) {
            // 수정
            CourseEvaluation existing = evalRepo
                    .findByUserIdAndCourseId(userId, courseId)
                    .orElseThrow();
            existing.setRating(req.rating());
            existing.setSemesterYear(req.semesterYear());
            existing.setSemesterTerm(req.semesterTerm());
            existing.setContent(req.content());
            CourseEvaluation saved = evalRepo.save(existing);

            User u = userRepo.findById(userId).orElseThrow();
            return new EvaluationDto(
                    saved.getId(),
                    saved.getUserId(),
                    u.getNickname(),
                    saved.getCourseId(),
                    saved.getRating(),
                    saved.getSemesterYear(),
                    saved.getSemesterTerm(),
                    saved.getContent(),
                    saved.getCreatedAt()
            );
        } else {
            // 신규 생성
            CourseEvaluation e = new CourseEvaluation(
                    userId,
                    courseId,
                    req.rating(),
                    req.semesterYear(),
                    req.semesterTerm(),
                    req.content()
            );
            CourseEvaluation saved = evalRepo.save(e);

            User u = userRepo.findById(userId).orElseThrow();
            return new EvaluationDto(
                    saved.getId(),
                    saved.getUserId(),
                    u.getNickname(),
                    saved.getCourseId(),
                    saved.getRating(),
                    saved.getSemesterYear(),
                    saved.getSemesterTerm(),
                    saved.getContent(),
                    saved.getCreatedAt()
            );
        }
    }
}
