package com.github.fmatt.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.jsf",
                errorPage = "/login.jsf?error=true")
)
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "tmpDS",
        callerQuery = "SELECT password FROM users WHERE login = ?",
        groupsQuery = """
            SELECT g.name
            FROM users u
            JOIN users_groups ug ON u.id = ug.user_id
            JOIN groups g ON ug.group_id = g.id
            WHERE u.login = ?""",
        hashAlgorithm = Base64Sha256PasswordHash.class
)
@ApplicationScoped
public class ApplicationConfig {

}
