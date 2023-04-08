# Reference documentation

https://eclipse-ee4j.github.io/jakartaee-tutorial/#using-websockets-with-jakarta-faces-technology

# Configuration 

```
/subsystem=elytron/jdbc-realm=myJdbcRealm:add(principal-query=[{sql="SELECT password FROM users WHERE login=?",data-source=ExampleDS,simple-digest-mapper={algorithm=simple-digest-sha-256,hash-encoding=base64,password-index=1}}, {sql="SELECT g.name FROM users u JOIN users_groups ug ON u.id = ug.user_id JOIN groups g ON ug.group_id = g.id WHERE u.login=?",data-source=ExampleDS, attribute-mapping=[{index=1,to=groups}]}])
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
