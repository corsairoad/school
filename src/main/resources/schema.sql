CREATE TABLE student (
  _id INTEGER PRIMARY KEY AUTO_INCREMENT,
  student_name VARCHAR(40),
  student_id VARCHAR(20)
);
/*
create table users(
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(50) not null,
	enabled boolean not null
);

create table authorities (
	username varchar_ignorecase(50) not null,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);
*/

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL PRIMARY KEY ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1);

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL);