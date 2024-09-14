CREATE DATABASE tmp;

CREATE TABLE foos (
    id SERIAL,
    description VARCHAR(20),
    status CHAR(1),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);

INSERT INTO foos (description, status) VALUES ('ABC', 'A'), ('DEF', 'I'), ('GHI', 'O');
