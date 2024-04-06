CREATE DATABASE tmp;

CREATE TABLE foos (
    id SERIAL,
    description VARCHAR(20),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);

INSERT INTO foos (description) VALUES ('ABC'), ('DEF'), ('GHI');
