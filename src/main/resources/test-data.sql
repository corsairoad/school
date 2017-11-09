INSERT INTO student
(`_id`,
`student_name`,
`student_id`)
VALUES
(1,
'Fadly Munandar',
'201081039');

INSERT INTO student
(`_id`,
`student_name`,
`student_id`)
VALUES
(2,
'Yuni Ghaitsani',
'201081040');

INSERT INTO student
(`_id`,
`student_name`,
`student_id`)
VALUES
(3,
'Keenandra',
'201081041');

/*
insert into users
VALUES ('fadly', 'admin123', 1);

insert into authorities
VALUES ('fadly', 'ADMIN');
*/

INSERT INTO users(username,password,enabled)
VALUES ('fadly','fadly', 1);

INSERT INTO users(username,password,enabled)
VALUES ('user','user', 1);

INSERT INTO user_roles (username, role)
VALUES ('fadly', 'ROLE_USER');

INSERT INTO user_roles (username, role)
VALUES ('fadly', 'ROLE_ADMIN');

INSERT INTO user_roles (username, role)
VALUES ('user', 'user');