package com.github.fmatt.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;


@Provider
public class CustomClientExceptionMapper implements ResponseExceptionMapper<RuntimeException> {

    @Override
    public RuntimeException  toThrowable(Response response) {
         if (response.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {
             String errorMessage = response.readEntity(String.class);
             return new RuntimeException(new CustomRestException(errorMessage));
         }

         return null;
    }

}
