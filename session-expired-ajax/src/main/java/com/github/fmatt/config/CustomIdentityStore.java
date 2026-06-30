package com.github.fmatt.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class CustomIdentityStore implements IdentityStore {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    public CredentialValidationResult validate(UsernamePasswordCredential credential) {
        try {
            String username = credential.getCaller();
            String password = credential.getPasswordAsString();

            if ("admin".equals(username) && "admin".equals(password))
                return new CredentialValidationResult(username.toLowerCase(), Set.of("ADMIN"));


            return CredentialValidationResult.INVALID_RESULT;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return CredentialValidationResult.INVALID_RESULT;
        }
    }

}
