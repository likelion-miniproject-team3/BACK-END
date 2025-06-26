package com.example.majorapp.service;

import com.example.majorapp.dto.CreateExamRequest;
import com.example.majorapp.dto.ExamDto;
import com.example.majorapp.entity.CourseExam;
import com.example.majorapp.entity.User;
import com.example.majorapp.repository.CourseExamRepository;
import com.example.majorapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

    private final CourseExamRepository examRepo;
    private final FileStorageService storage;
    private final UserRepository userRepo;

    public ExamServiceImpl(
            CourseExamRepository examRepo,
            FileStorageService storage,
            UserRepository userRepo
    ) {
        this.examRepo = examRepo;
        this.storage  = storage;
        this.userRepo = userRepo;
    }

    @Override
    public List<ExamDto> getExams(Integer courseId) {
        return examRepo.findByCourseId(courseId).stream()
                .map(e -> {
                    String nick = userRepo.findById(e.getUserId())
                            .map(User::getNickname)
                            .orElse("익명");
                    return new ExamDto(
                            e.getExamId(),
                            e.getUserId(),
                            nick,
                            e.getCourseId(),
                            e.getSemesterYear(),
                            e.getSemesterTerm(),
                            e.getAttemptType(),
                            e.getContent(),
                            e.getFileUrls(),
                            e.getCreatedAt()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public ExamDto createOrUpdateExam(
            String username,
            Integer courseId,
            CreateExamRequest req,
            List<MultipartFile> files
    ) throws IOException {
        // 1) username → User → userId
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Long userId = user.getId();

        // 2) 기존 레코드 조회 or 신규 생성
        CourseExam exam = examRepo
                .findByUserIdAndCourseIdAndSemesterYearAndSemesterTermAndAttemptType(
                        userId,
                        courseId,
                        req.semesterYear(),
                        req.semesterTerm(),
                        req.attemptType()
                )
                .orElseGet(() -> new CourseExam(
                        userId,
                        courseId,
                        req.semesterYear(),
                        req.semesterTerm(),
                        req.attemptType(),
                        req.content(),
                        List.of()
                ));

        // 3) 내용 및 파일 저장
        exam.setContent(req.content());
        exam.setFileUrls(storage.storeAll(files));

        CourseExam saved = examRepo.save(exam);

        String nick = userRepo.findById(saved.getUserId())
                .map(User::getNickname)
                .orElse("익명");

        return new ExamDto(
                saved.getExamId(),
                saved.getUserId(),
                nick,
                saved.getCourseId(),
                saved.getSemesterYear(),
                saved.getSemesterTerm(),
                saved.getAttemptType(),
                saved.getContent(),
                saved.getFileUrls(),
                saved.getCreatedAt()
        );
    }
}
