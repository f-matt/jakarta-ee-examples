# Context

This is the main "client" project, which will consume rest API of the "server". 

# Wildfly

## Running:

```
$ mvn clean install wildfly:dev

$ curl http://localhost:8081/project-b/index
$ curl http://localhost:8081/project-b/fail
```
