package com.github.fmatt.client;

import com.github.fmatt.dto.SampleDto;
import com.github.fmatt.exception.UnknownUriException;
import com.github.fmatt.exception.UnknownUriExceptionMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "sampleClient")
@RegisterProvider(UnknownUriExceptionMapper.class)
@Path("sample")
@Produces(MediaType.APPLICATION_JSON)
public interface SampleClient {

    @GET
    SampleDto get() throws UnknownUriException;
}
