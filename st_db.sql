CREATE DATABASE studentdb;
USE studentdb;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    course VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE
);

TRUNCATE TABLE students;
SELECT * FROM students;