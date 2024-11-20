package com.github.fmatt.endpoint;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/index")
public class IndexEndpoint {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response index() {
        return Response.ok("ok!").build();
    }

}
