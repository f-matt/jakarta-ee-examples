CREATE TABLE users (id INT, login VARCHAR (30), password VARCHAR (256), PRIMARY KEY (id));

CREATE TABLE groups (id INT, name VARCHAR (30), PRIMARY KEY (id));

CREATE TABLE users_groups (user_id INT, group_id INT);

-- user / 123
INSERT INTO users (id, login, password) VALUES (1, 'user', 'pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=');

INSERT INTO groups (id, name) VALUES (1, 'ADMIN');

INSERT INTO users_groups (user_id, group_id) VALUES (1, 1);
