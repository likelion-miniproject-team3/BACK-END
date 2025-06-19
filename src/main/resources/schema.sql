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
