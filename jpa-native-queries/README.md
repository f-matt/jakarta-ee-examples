# Wildfly Deployment

## 1. Postgresql JDBC Driver

```declarative
wget https://jdbc.postgresql.org/download/postgresql-42.7.4.jar

cli> module add --name=org.postgresql --resources=<path>/postgresql-42.7.4.jar --dependencies=javax.api,javax.transaction.api
cli> /subsystem=datasources/jdbc-driver=postgresql:add(driver-name="postgresql", driver-module-name="org.postgresql",driver-class-name=org.postgresql.Driver)
```

## 2. Datasource

```declarative
cli> data-source add --jndi-name=java:/tmpDS --name=tmpDS --connection-url=jdbc:postgresql://localhost:5432/tmp --driver-name=postgresql --user-name=<user> --password=<password>
cli> /subsystem=datasources/data-source=tmpDS:test-connection-in-pool()
```

## 3. Deploy

```declarative
$ mvn clean install wildfly:deploy
```