package com.github.fmatt.endpoints;

import com.github.fmatt.client.FailClient;
import com.github.fmatt.exception.CustomRestException;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("fail")
@Produces(MediaType.APPLICATION_JSON)
public class FailEndpoint {

    private final Logger logger = Logger.getAnonymousLogger();

    @Inject
    @RestClient
    private FailClient failClient;

    @GET
    public Response get() {
        try {
            return Response.ok(failClient.get()).build();
        } catch (RuntimeException e) {
            if (e.getCause() instanceof CustomRestException exception)
                return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();

            return Response.status(Response.Status.BAD_REQUEST).entity("Error processing request.").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return Response.status(Response.Status.BAD_REQUEST).entity("Error processing request.").build();
        }
    }
}
