-- schema.sql
CREATE TABLE IF NOT EXISTS majors (
                                      major_id     INT PRIMARY KEY,
                                      code         VARCHAR(50),
                                      name         VARCHAR(100),
                                      description  TEXT,
                                      sort_order   INT
);

CREATE TABLE IF NOT EXISTS courses (
                                       course_id    INT PRIMARY KEY,
                                       code         VARCHAR(50),
                                       name         VARCHAR(200),
                                       description  TEXT,
                                       year         INT,
                                       semester     INT,
                                       credit       INT,
                                       is_required  BOOLEAN
);

CREATE TABLE IF NOT EXISTS course_major_rel (
                                                course_id INT,
                                                major_id  INT,
                                                PRIMARY KEY (course_id, major_id),
                                                FOREIGN KEY (course_id) REFERENCES courses(course_id),
                                                FOREIGN KEY (major_id ) REFERENCES majors(major_id)
);
-- 1) 사용자 테이블
CREATE TABLE IF NOT EXISTS users (
                                     user_id      BIGINT         NOT NULL AUTO_INCREMENT,
                                     username     VARCHAR(50)    NOT NULL UNIQUE,      -- 로그인 ID
                                     password     VARCHAR(100)   NOT NULL,             -- 해시된 비밀번호
                                     name         VARCHAR(50)    NOT NULL,             -- 실명
                                     nickname     VARCHAR(50),                         -- 별명(선택)
                                     email        VARCHAR(100)   NOT NULL UNIQUE,      -- 이메일
                                     student_id   VARCHAR(20)    UNIQUE,               -- 학번
                                     major_id     INT            NOT NULL,             -- 선택 전공
                                     PRIMARY KEY (user_id),
                                     FOREIGN KEY (major_id) REFERENCES majors(major_id)
);

-- 2) 가입 세션 임시 저장 테이블
CREATE TABLE IF NOT EXISTS registration_sessions (
                                                     temp_id      CHAR(36)       NOT NULL,             -- UUID 같은 임시 세션키
                                                     username     VARCHAR(50),                         -- step1 입력값
                                                     password     VARCHAR(100),                       -- step1 비밀번호 (평문)
                                                     name         VARCHAR(50),                         -- step2 입력값
                                                     nickname     VARCHAR(50),                         -- step2 입력값
                                                     email        VARCHAR(100),                        -- step2 입력값
                                                     student_id   VARCHAR(20),                         -- step2 입력값
                                                     major_id     INT,                                 -- step3 선택값
                                                     step         TINYINT        NOT NULL,             -- 현재 진행 스텝 번호
                                                     created_at   DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                     updated_at   DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                     PRIMARY KEY (temp_id),
                                                     FOREIGN KEY (major_id) REFERENCES majors(major_id)
);
-- 1) 강의평(수강후기) 테이블
CREATE TABLE IF NOT EXISTS course_evaluations (
                                                  evaluation_id   BIGINT         NOT NULL AUTO_INCREMENT,
                                                  course_id       INT            NOT NULL,
                                                  user_id         BIGINT         NOT NULL,
                                                  rating          TINYINT        NOT NULL CHECK (rating BETWEEN 1 AND 5),
                                                  semester_year   INT            NOT NULL,               -- 이수 연도(예: 2025)
                                                  semester_term   VARCHAR(10)    NOT NULL,               -- 이수 학기(예: '1학기', '2학기', '계절학기')
                                                  title           VARCHAR(200)   NOT NULL,               -- 후기 제목
                                                  content         TEXT           NOT NULL,               -- 후기 내용
                                                  created_at      DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                  PRIMARY KEY (evaluation_id),
                                                  FOREIGN KEY (course_id) REFERENCES courses(course_id),
                                                  FOREIGN KEY (user_id)   REFERENCES users(user_id)
);

-- 2) 시험정보 테이블
CREATE TABLE IF NOT EXISTS course_exams (
                                            exam_id         BIGINT         NOT NULL AUTO_INCREMENT,
                                            course_id       INT            NOT NULL,
                                            semester_year   INT            NOT NULL,               -- 응시 연도
                                            semester_term   VARCHAR(10)    NOT NULL,               -- 응시 학기
                                            attempt_type    VARCHAR(20)    NOT NULL,               -- 회차(예: '중간고사', '기말고사')
                                            content         TEXT           NULL,                   -- 시험 설명/주의사항
                                            file_url        VARCHAR(500)   NULL,                   -- 업로드된 PDF 파일 URL
                                            created_at      DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                            PRIMARY KEY (exam_id),
                                            FOREIGN KEY (course_id) REFERENCES courses(course_id)
);
-- 북마크용 테이블 정의
CREATE TABLE IF NOT EXISTS user_bookmarks (
                                              user_id     BIGINT    NOT NULL,
                                              course_id   INT       NOT NULL,
                                              created_at  DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                              PRIMARY KEY (user_id, course_id),
                                              FOREIGN KEY (user_id)   REFERENCES users(user_id),
                                              FOREIGN KEY (course_id) REFERENCES courses(course_id)
);
-- 이미 수강한 과목(수강내역) 테이블
CREATE TABLE IF NOT EXISTS user_enrollments (
                                                enrollment_id BIGINT      NOT NULL AUTO_INCREMENT,
                                                user_id       BIGINT      NOT NULL,
                                                course_id     INT         NOT NULL,
                                                enrolled_at   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                PRIMARY KEY (enrollment_id),
                                                UNIQUE KEY uk_user_course (user_id, course_id),
                                                FOREIGN KEY (user_id)   REFERENCES users(user_id),
                                                FOREIGN KEY (course_id) REFERENCES courses(course_id)
);
-- course_exams 테이블이 이미 있어야 합니다.
-- 8) 시험정보 파일 URL을 담는 조인 테이블
CREATE TABLE IF NOT EXISTS course_exam_files (
                                                 exam_id  BIGINT      NOT NULL,
                                                 file_url VARCHAR(500) NOT NULL,
                                                 PRIMARY KEY (exam_id, file_url),
                                                 FOREIGN KEY (exam_id) REFERENCES course_exams(exam_id)
);
