package com.aldebaran.server.configuration;

import com.aldebaran.rest.handlers.GeneralExceptionHandler;
import com.aldebaran.rest.handlers.ValidationExceptionHandler;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;


@Configuration
@ApplicationPath("/")
public class JerseyConfiguration extends ResourceConfig {

    /** Package for jersey components */
    public static final String componentScan = "com.aldebaran";

    @Autowired
    public JerseyConfiguration(ValidationExceptionHandler cHandler, GeneralExceptionHandler gHandler) {
        packages(componentScan);
        register(cHandler);
        register(gHandler);

        initializeSwagger();
    }

    private void initializeSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/");
        beanConfig.setResourcePackage(componentScan);
        beanConfig.setScan(true);

        final Resource.Builder docsBuilder = Resource.builder();
        docsBuilder
            .path("api-docs/")
            .addChildResource(Resource.builder(ApiListingResource.class)
            .build());
        registerResources(docsBuilder.build());
        register(SwaggerSerializers.class);
    }
}