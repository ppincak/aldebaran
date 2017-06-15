package com.aldebaran.server.config;

import com.aldebaran.chassis.discovery.DiscoveryProperties;
import com.aldebaran.chassis.monitoring.controller.MonitoringController;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;


@Configuration
@ApplicationPath("/")
public class JerseyConfiguration extends ResourceConfig {

    @Autowired
    public JerseyConfiguration(DiscoveryProperties discoveryProperties) {
        register(MultiPartFeature.class);
        register(MonitoringController.class);
        packages("com.aldebaran.api",
                 "com.aldebaran.rest");

        initializeSwagger(discoveryProperties);
    }

    private void initializeSwagger(DiscoveryProperties discoveryProperties) {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost(discoveryProperties.assembleServiceUrl());
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