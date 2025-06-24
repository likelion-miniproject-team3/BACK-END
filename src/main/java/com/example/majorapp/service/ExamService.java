package com.example.majorapp.service;

import com.example.majorapp.dto.CreateExamRequest;
import com.example.majorapp.dto.ExamDto;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ExamService {
    /**
     * 특정 과목에 작성된 모든 유저의 시험정보 조회
     */
    List<ExamDto> getExams(Integer courseId);

    /**
     * 특정 유저의 시험정보 등록 또는 수정
     */
    ExamDto createOrUpdateExam(
            Integer courseId,
            CreateExamRequest req,
            List<MultipartFile> files
    ) throws IOException;
}