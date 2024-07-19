# Database

```
psql> CREATE USER tmp WITH PASSWORD '12345';
psql> CREATE DATABASE tmp WITH OWNER tmp;
```

## Connect to tmpclass CustomRevisionListener database:

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

CREATE SEQUENCE revision_entity_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE revision_entity (
    id SERIAL,
    timestamp BIGINT,
    username VARCHAR (20),
    PRIMARY KEY (id)
);

CREATE TABLE items_aud (
    id INTEGER,
    rev INTEGER,
    revtype SMALLINT,
    description VARCHAR (10),
    PRIMARY KEY (id, rev)
);



```

# Wildfly

## Driver (postgresql):

```
$ cd /tmp
$ wget https://jdbc.postgresql.org/download/postgresql-42.7.3.jar
jboss-cli> module add --name=org.postgresql --resources=/tmp/postgresql-42.7.3.jar --dependencies=javax.api,javax.transaction.api
jboss-cli> /subsystem=datasources/jdbc-driver=postgresql:add(driver-name="postgresql", driver-module-name="org.postgresql",driver-class-name=org.postgresql.Driver)
```

## Datasource:

```
jboss-cli> data-source add --jndi-name=java:/tmpDS --name=tmpDS --connection-url=jdbc:postgresql://localhost/tmp --driver-name=postgresql --user-name=tmp --password=12345
jboss-cli> /subsystem=datasources/data-source=tmpDS:test-connection-in-pool()
```
