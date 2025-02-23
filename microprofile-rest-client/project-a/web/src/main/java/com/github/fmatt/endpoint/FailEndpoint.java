package com.github.fmatt.endpoint;

import com.github.fmatt.exception.CustomRestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("fail")
@Produces(MediaType.APPLICATION_JSON)
public class FailEndpoint {

    @GET
    public Response get() {
        throw new CustomRestException("This is the exception message.");
    }

}
