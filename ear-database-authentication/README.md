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


CREATE TABLE users (
    id SERIAL,
    username VARCHAR(100),
    password VARCHAR(256),
    role VARCHAR(50),
    PRIMARY KEY (id)
);

INSERT INTO users (username, password, role) VALUES ('admin', encode(sha256('12345'), 'base64'), 'ADMIN');


CREATE SCHEMA audit;

CREATE SEQUENCE revinfo_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE revinfo (
    id INTEGER NOT NULL DEFAULT NEXTVAL('revinfo_seq'),
    timestamp BIGINT,
    username VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE items_aud (
    id INTEGER NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    description VARCHAR (10),
    PRIMARY KEY (id, rev)
);
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

## Authentication 

```
/subsystem=elytron/jdbc-realm=myJdbcRealm:add(principal-query=[{sql="SELECT password FROM users WHERE username=?",data-source=tmpDS,simple-digest-mapper={algorithm=simple-digest-sha-256,hash-encoding=base64,password-index=1}}, {sql="SELECT role FROM users u WHERE u.username=?",data-source=tmpDS, attribute-mapping=[{index=1,to=groups}]}])
```

```
/subsystem=elytron/security-domain=mySecurityDomain:add(realms=[{realm=myJdbcRealm,role-decoder=groups-to-roles}],default-realm=myJdbcRealm,permission-mapper=default-permission-mapper)
```

```
/subsystem=elytron/http-authentication-factory=myHttpAuthenticationFactory:add(http-server-mechanism-factory=global,security-domain=mySecurityDomain,mechanism-configurations=[{mechanism-name=FORM,mechanism-realm-configurations=[{realm-name=mySecurityDomain}]}])
```

```
/subsystem=undertow/application-security-domain=myApplicationSecurityDomain:add(http-authentication-factory=myHttpAuthenticationFactory)
```

```
/subsystem=ejb3/application-security-domain=myApplicationSecurityDomain:add(security-domain=mySecurityDomain)
```