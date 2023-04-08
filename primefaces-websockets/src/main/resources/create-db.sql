CREATE TABLE users (id INT, login VARCHAR (30), password VARCHAR (256), PRIMARY KEY (id));

CREATE TABLE groups (id INT, name VARCHAR (30), PRIMARY KEY (id));

CREATE TABLE users_groups (user_id INT, group_id INT);
