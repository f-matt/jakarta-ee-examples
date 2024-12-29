# Context

In this example, two distinct data sources (one running on PostgreSQL and the other on MariaDB) are used by the same web 
application. Two persistence units are setup, both of them avaiable to a single war project.

# PostgreSQL Database

```
CREATE DATABASE tmp;

-- On database tmp
CREATE TABLE table_a (
    id SERIAL,
    description VARCHAR (10),
    PRIMARY KEY (id)
);

INSERT INTO table_a (description) values ('ABC'), ('DEF');
```

# MariaDB Database

```
CREATE DATABASE tmp;

-- On database tmp
CREATE TABLE table_b (
    id SERIAL,
    description VARCHAR (10),
    PRIMARY KEY (id)
);

INSERT INTO table_b (description) values ('123'), ('456');
```

# Wildfly

## Running:

```
$ mvn wildfly:dev
```
