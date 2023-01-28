CREATE TABLE students (     student_id INT PRIMARY KEY,     first_name VARCHAR(255),     last_name VARCHAR(255),     email VARCHAR(255),     gender VARCHAR(255),     ip_address VARCHAR(255),     height INT,     age INT,     has_car BOOLEAN,     car_color VARCHAR(255),     grade INT,     grade_avg DOUBLE,     id_number INT );
CREATE TABLE friends (     friendship_id INT,     friend_id INT,     other_friend_id INT,     PRIMARY KEY (friendship_id),     FOREIGN KEY (friend_id) REFERENCES students(student_id),     FOREIGN KEY (other_friend_id) REFERENCES students(student_id) );

CREATE VIEW avg_score AS 
SELECT student_id, grade_avg as avg_score
FROM students
GROUP BY student_id;
