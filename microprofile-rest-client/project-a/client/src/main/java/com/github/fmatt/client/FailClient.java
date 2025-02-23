package com.github.fmatt.client;

import com.github.fmatt.dto.SampleDto;
import com.github.fmatt.exception.CustomClientExceptionMapper;
import com.github.fmatt.exception.CustomRestExceptionMapper;
import com.github.fmatt.exception.UnknownUriException;
import com.github.fmatt.exception.UnknownUriExceptionMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "failClient")
@RegisterProvider(CustomRestExceptionMapper.class)
@RegisterProvider(CustomClientExceptionMapper.class)
@Path("fail")
@Produces(MediaType.APPLICATION_JSON)
public interface FailClient {

    @GET
    SampleDto get() throws UnknownUriException;
}
