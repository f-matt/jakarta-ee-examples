package com.github.fmatt.endpoints;

import com.github.fmatt.client.SampleClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("index")
@Produces(MediaType.APPLICATION_JSON)
public class IndexEndpoint {

    private Logger logger = Logger.getAnonymousLogger();

    @Inject
    @RestClient
    private SampleClient sampleClient;

    @GET
    public Response get() {
        try {
            return Response.ok(sampleClient.get()).build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return Response.status(Response.Status.BAD_REQUEST).entity("Error processing request.").build();
        }
    }
}
