package com.aldebaran.order.server.config;

import com.aldebaran.chassis.monitoring.controller.MonitoringController;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;


@Configuration
@ApplicationPath("/")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        register(MultiPartFeature.class);
        register(MonitoringController.class);
        packages("com.aldebaran.order.api",
                 "com.aldebaran.chassis",
                 "com.aldebaran.rest");

        initializeSwagger();
    }

    private void initializeSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost");
        beanConfig.setBasePath("/");
        beanConfig.setResourcePackage("com.aldebaran");
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