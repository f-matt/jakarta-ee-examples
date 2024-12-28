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

# Payara

## Driver (postgresql):

```
$ <payara-home>/bin/asadmin add-library payara/driver/postgresql-42.7.4.jar
```

## Data source:

```
$ <payara-home>/bin/asadmin create-jdbc-connection-pool --datasourceClassname=org.postgresql.ds.PGSimpleDataSource --resType=javax.sql.DataSource tmp-pool

$ <payara-home>/bin/asadmin set resources.jdbc-connection-pool.tmp-pool.property.serverName=localhost
$ <payara-home>/bin/asadmin set resources.jdbc-connection-pool.tmp-pool.property.portNumber=5432
$ <payara-home>/bin/asadmin set resources.jdbc-connection-pool.tmp-pool.property.databaseName=tmp
$ <payara-home>/bin/asadmin set resources.jdbc-connection-pool.tmp-pool.property.user=postgres
$ <payara-home>/bin/asadmin set resources.jdbc-connection-pool.tmp-pool.property.password=123456

$ <payara-home>/bin/asadmin ping-connection-pool tmp-pool

$ <payara-home>/bin/asadmin create-jdbc-resource --enabled=true --poolName=tmp-pool --target=domain jdbc/tmp
$ <payara-home>/bin/asadmin create-resource-ref --enabled=true --target=server jdbc/tmp
```

## Deploy:

```
$ <payara-home>/bin/asadmin deploy ear/target/ear.ear
```
