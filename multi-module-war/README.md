# Context

In this example, two distinct data sources (one running on PostgreSQL and the other on MariaDB) are used by the same web 
application. Two entities projects are created, each one with its own persistence unit. Also, two EJBs projects are also
set up, one for each database.

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
$ mvn clean install -pl web wildfly:dev
```
