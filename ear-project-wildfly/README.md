# Database

```
psql> CREATE USER tmp WITH PASSWORD '12345';
psql> CREATE DATABASE tmp WITH OWNER tmp;
```

## Connect to tmp database:

```
$ psql -U tmp -W -h localhost tmp
```

## Create tables:

```
CREATE TABLE items (
    id SERIAL,
    description VARCHAR (10),
    PRIMARY KEY (id)
);

INSERT INTO items (description) values ('ABC');
```

# Wildfly

## Running:

```
$ mvn -pl war wildfly:dev
```
