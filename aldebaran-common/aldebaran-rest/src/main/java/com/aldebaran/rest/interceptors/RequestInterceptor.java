package com.aldebaran.rest.interceptors;

import com.aldebaran.rest.device.DeviceConstants;
import com.aldebaran.rest.device.DeviceContext;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
public class RequestInterceptor implements ContainerRequestFilter {

    private String[] apiKeys;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String lang = requestContext.getHeaderString(DeviceConstants.LANG_HEADER);
        String apiKey = requestContext.getHeaderString(DeviceConstants.API_KEY_HEADER);
        DeviceContext.setContext(new DeviceContext(lang, apiKey));
    }
}
