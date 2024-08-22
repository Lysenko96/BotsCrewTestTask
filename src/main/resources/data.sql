INSERT INTO lector(first_name, last_name, degree, salary) VALUES ('Tony', 'Stark', 'PROFESSOR', 1);
INSERT INTO lector(first_name, last_name, degree, salary) VALUES ('John', 'Wick', 'PROFESSOR', 99);
INSERT INTO department(name, head) VALUES('department', 'Tony');
INSERT INTO lectors_departments(department_id, lector_id) VALUES(1, 1);
INSERT INTO lectors_departments(department_id, lector_id) VALUES(1, 2);