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

## Driver (postgresql):

```
$ cd /tmp
$ wget https://jdbc.postgresql.org/download/postgresql-42.5.0.jar
jboss-cli> module add --name=org.postgresql --resources=/tmp/postgresql-42.5.0.jar --dependencies=javax.api,javax.transaction.api
jboss-cli> /subsystem=datasources/jdbc-driver=postgresql:add(driver-name="postgresql", driver-module-name="org.postgresql",driver-class-name=org.postgresql.Driver)
```

## Datasource:

```
jboss-cli> data-source add --jndi-name=java:/tmpDS --name=tmpDS --connection-url=jdbc:postgresql://localhost/tmp --driver-name=postgresql --user-name=tmp --password=12345
jboss-cli> /subsystem=datasources/data-source=tmpDS:test-connection-in-pool()
```
