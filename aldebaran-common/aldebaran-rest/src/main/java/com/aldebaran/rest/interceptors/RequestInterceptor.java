package com.aldebaran.rest.interceptors;

import com.aldebaran.rest.RestUtils;
import com.aldebaran.rest.ServiceProperties;
import com.aldebaran.rest.device.DeviceConstants;
import com.aldebaran.rest.device.DeviceContext;
import com.aldebaran.rest.handlers.ErrorResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.activity.InvalidActivityException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.InvalidKeyException;


@Provider
@ApplyDeviceContext
public class RequestInterceptor implements ContainerRequestFilter {

    @Autowired
    private ServiceProperties serviceProperties;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String lang = requestContext.getHeaderString(DeviceConstants.API_LANG_HEADER);
        String apiKey = requestContext.getHeaderString(DeviceConstants.API_KEY_HEADER);
        if(RestUtils.hasApiKey(serviceProperties.getApiKeys(), apiKey) == false) {
            throw new ApiKeyException();
        }
        DeviceContext.setContext(new DeviceContext(lang, apiKey));
    }
}
