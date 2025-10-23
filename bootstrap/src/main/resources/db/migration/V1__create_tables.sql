-- Tabela de alunos
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

-- Tabela de cursos
CREATE TABLE courses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

-- Tabela de relação entre aluno e curso
CREATE TABLE student_courses (
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    enrolled_at TIMESTAMP,
    status VARCHAR(50),
    PRIMARY KEY (student_id, course_id),
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students(id),
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- Tabela de lições (aulas)
CREATE TABLE lessons (
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_id INT NOT NULL,
    title VARCHAR(150) NOT NULL,
    duration_seconds INT NOT NULL,
    sequence INT NOT NULL,  -- ordem da lição dentro do curso
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_course_lesson FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- Tabela para armazenar progresso do aluno em cada lição
CREATE TABLE student_lessons_progress (
    id INT AUTO_INCREMENT,
    student_id INT NOT NULL,
    lesson_id INT NOT NULL,
    percentage_of_progress INT,
    completed_at TIMESTAMP NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_student_lesson (student_id, lesson_id), -- Added a UNIQUE constraint for the progress combination
    CONSTRAINT fk_student_lessons_progress FOREIGN KEY (student_id) REFERENCES students(id),
    CONSTRAINT fk_lesson_progress FOREIGN KEY (lesson_id) REFERENCES lessons(id)
);

-- Tabela para armazenar progresso geral do aluno em um curso
CREATE TABLE student_progress (
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    progress_percent DECIMAL(5,2) DEFAULT 0,
    last_updated TIMESTAMP,
    PRIMARY KEY (student_id, course_id),
    CONSTRAINT fk_student_progress FOREIGN KEY (student_id) REFERENCES students(id),
    CONSTRAINT fk_course_progress FOREIGN KEY (course_id) REFERENCES courses(id)
);