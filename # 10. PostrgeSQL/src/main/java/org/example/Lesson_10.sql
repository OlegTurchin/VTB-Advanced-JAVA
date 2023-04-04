DROP TABLE IF EXISTS students, subjects, performance;

CREATE TABLE IF NOT EXISTS students
(
    id       SERIAL PRIMARY KEY,
    name     CHAR(10) NOT NULL,
    pass_ser SMALLINT NOT NULL UNIQUE,
    pass_num INT NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS subjects
(
    id   SMALLSERIAL PRIMARY KEY,
    name CHAR(10) NOT NULL
);
CREATE TABLE IF NOT EXISTS performance
(
    id      SMALLSERIAL PRIMARY KEY,
    student SMALLINT,
    subject SMALLINT,
    mark    SMALLINT CHECK ( 2 < mark AND mark < 6 ),
    FOREIGN KEY (student) REFERENCES students (id) ON DELETE CASCADE,
    FOREIGN KEY (subject) REFERENCES subjects (id) ON DELETE CASCADE
);

INSERT INTO students (name, pass_ser, pass_num)
VALUES ('Bob', 6352, 937353);
INSERT INTO students (name, pass_ser, pass_num)
VALUES ('John', 5436, 853345);
INSERT INTO students (name, pass_ser, pass_num)
VALUES ('Sam', 8335, 534542);

INSERT INTO subjects (name)
VALUES ('Maths');
INSERT INTO subjects (name)
VALUES ('Langs');
INSERT INTO subjects (name)
VALUES ('Physx');
INSERT INTO subjects (name)
VALUES ('Gyms');

INSERT INTO performance (student, subject, mark)
VALUES (1, 2, 4);
INSERT INTO performance (student, subject, mark)
VALUES (2, 3, 4);
INSERT INTO performance (student, subject, mark)
VALUES (3, 4, 3);
INSERT INTO performance (student, subject, mark)
VALUES (1, 1, 4);
INSERT INTO performance (student, subject, mark)
VALUES (3, 2, 5);
-- INSERT INTO performance (student, subject, mark)
-- VALUES (2, 3, 2);

SELECT * FROM students;
SELECT * FROM subjects;
SELECT * FROM performance;

SELECT student FROM performance WHERE subject = 2 AND mark > 3;
SELECT avg(mark)FROM performance WHERE subject = 2;
SELECT avg(mark)FROM performance WHERE student = 2;



SELECT students.name, subjects.name, performance.mark
FROM performance
         JOIN students ON performance.student = students.id
         JOIN subjects ON performance.subject = subjects.id;