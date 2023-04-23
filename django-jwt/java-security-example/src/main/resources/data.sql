-- admin / 123
INSERT INTO users (id, login, password) VALUES (1, 'admin', 'pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=');

-- user / 123
INSERT INTO users (id, login, password) VALUES (2, 'user', 'pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=');

INSERT INTO groups (id, name) VALUES (1, 'ADMIN');
INSERT INTO groups (id, name) VALUES (2, 'USER');

INSERT INTO users_groups (user_id, group_id) VALUES (1, 1);
INSERT INTO users_groups (user_id, group_id) VALUES (1, 2);
INSERT INTO users_groups (user_id, group_id) VALUES (2, 2);
