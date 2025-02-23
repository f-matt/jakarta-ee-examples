package com.github.fmatt.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomRestExceptionMapper implements ExceptionMapper<CustomRestException> {

    @Override
    public Response toResponse(CustomRestException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }

}
