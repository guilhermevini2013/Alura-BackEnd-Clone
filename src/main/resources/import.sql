insert into categories (name) values ('Programming');
insert into categories (name) values ('Front-End');
insert into categories (name) values ('Data science');
insert into categories (name) values ('Mobile');
insert into certificate (name_certificate,total_hours) values ('java',10);
INSERT INTO content (assessment, certificate_id, description, name_content, publication_date, total_hours, total_student, type_content) VALUES (2, 1, 'Cool curse', 'java', '2023-12-16', 10, 0, 'Course');
INSERT INTO video_lesson (archive, course_id, duration, name_lesson) VALUES ('/java', 1, 11, 'POO em JAVA');

insert into certificate (name_certificate,total_hours) values ('JPA e suas dificuldades',10);
INSERT INTO content (assessment, certificate_id, description, name_content, publication_date, total_hours, total_student, type_content) VALUES (2, 2, 'Cool curse', 'java-JPA', '2023-12-13', 10, 0, 'Course');
INSERT INTO video_lesson (archive, course_id, duration, name_lesson) VALUES ('/java', 2, 13, 'POO em JAVA');

insert into certificate (name_certificate,total_hours) values ('JPA e suas difi',10);
INSERT INTO content (assessment, certificate_id, description, name_content, publication_date, total_hours, total_student, type_content) VALUES (3, 3, 'Cool curse', 'C# - POO', '2023-12-19', 10, 0, 'Course');
INSERT INTO video_lesson (archive, course_id, duration, name_lesson) VALUES ('/java', 3, 2, 'POO em JAVA');

insert into certificate (name_certificate,total_hours) values ('C# e suas difi',10);
INSERT INTO content (assessment, certificate_id, description, name_content, publication_date, total_hours, total_student, type_content) VALUES (3, 4, 'Cool curse', 'C# - POO', '2023-12-19', 10, 0, 'Course');
INSERT INTO video_lesson (archive, course_id, duration, name_lesson) VALUES ('/java', 4, 100, 'POO em JAVA');

INSERT INTO role (authority)VALUES ('ROLE_ADMIN');
INSERT INTO role (authority)VALUES ('ROLE_STUDENT');
INSERT INTO admin (id,email, password,is_Non_Locked,is_Non_Expired,is_Credentials_Non_Expired,is_Enabled) values (1,'admin@admin','$2a$10$LgBSfmfg26Ic7HZ7t1jKOeIxAq7y53VXjXg6yN2ehu7FfR1TNIP2q',true,true,true,true);
insert into user_role(id_user,id_role) values (1,1);