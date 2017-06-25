package com.aldebaran.rest.interceptors;

import com.aldebaran.rest.device.DeviceContext;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
@ApplyDeviceContext
public class ResponseInterceptor implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
        DeviceContext.setContext(null);
    }
}
