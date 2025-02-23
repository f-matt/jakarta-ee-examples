package com.github.fmatt.endpoint;

import com.github.fmatt.dto.SampleDto;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;

@Path("sample")
@Produces(MediaType.APPLICATION_JSON)
public class SampleEndpoint {

    @GET
    public Response get() {
        SampleDto dto = new SampleDto();
        dto.setId(1);
        dto.setDescription("A sample dto");
        dto.setCreatedAt(LocalDateTime.now());

        return Response.ok(dto).build();
    }

}
