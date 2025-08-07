-- Inserts na tabela students
INSERT INTO students (name, email) VALUES
('Alice Johnson', 'alice.johnson@example.com'),
('Bob Smith', 'bob.smith@example.com');

-- Inserts na tabela courses
INSERT INTO courses (title, description) VALUES
('English Basics', 'Introductory course covering basic English grammar and vocabulary.');

-- Inserts na tabela lessons (associadas ao curso)
INSERT INTO lessons (course_id, title, duration_seconds, sequence) VALUES
(1, 'Introduction to English', 600, 1),
(1, 'Basic Grammar Rules', 900, 2),
(1, 'Everyday Vocabulary', 1200, 3);

-- Matrícula dos alunos no curso
INSERT INTO student_courses (student_id, course_id, enrolled_at, status) VALUES
(1, 1, NOW(), 'enrolled'),
(2, 1, NOW(), 'enrolled');

-- Progresso em lições
INSERT INTO student_lessons_progress (student_id, lesson_id, completed_at) VALUES
(1, 1, NOW()),
(1, 2, NOW()),
(2, 1, NOW());

-- Progresso geral no curso
INSERT INTO student_progress (student_id, course_id, progress_percent, last_updated) VALUES
(1, 1, 66.67, NOW()),
(2, 1, 33.33, NOW());