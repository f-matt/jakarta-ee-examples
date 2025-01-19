package com.github.fmatt.exception;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

@Provider
public class UnknownUriExceptionMapper implements ResponseExceptionMapper<UnknownUriException> {

    @Override
    public UnknownUriException toThrowable(Response response) {
        return new UnknownUriException();
    }

    @Override
    public boolean handles(int status, MultivaluedMap<String, Object> headers) {
        return status == 404;
    }
}
