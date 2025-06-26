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
    private final UserRepository userRepo;

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
            String username,
            Integer courseId,
            CreateEvaluationRequest req
    ) {
        // 1) username → User 조회
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Long userId = user.getId();

        // 2) 이미 작성된 리뷰가 있으면 수정, 없으면 신규 생성
        if (evalRepo.existsByUserIdAndCourseId(userId, courseId)) {
            CourseEvaluation existing = evalRepo
                    .findByUserIdAndCourseId(userId, courseId)
                    .orElseThrow();
            existing.setRating(req.rating());
            existing.setSemesterYear(req.semesterYear());
            existing.setSemesterTerm(req.semesterTerm());
            existing.setContent(req.content());
            CourseEvaluation saved = evalRepo.save(existing);

            return toDto(saved, user.getNickname());
        } else {
            CourseEvaluation e = new CourseEvaluation(
                    userId,
                    courseId,
                    req.rating(),
                    req.semesterYear(),
                    req.semesterTerm(),
                    req.content()
            );
            CourseEvaluation saved = evalRepo.save(e);
            return toDto(saved, user.getNickname());
        }
    }

    // 공통 DTO 변환
    private EvaluationDto toDto(CourseEvaluation e, String nickname) {
        return new EvaluationDto(
                e.getId(),
                e.getUserId(),
                nickname,
                e.getCourseId(),
                e.getRating(),
                e.getSemesterYear(),
                e.getSemesterTerm(),
                e.getContent(),
                e.getCreatedAt()
        );
    }
}
