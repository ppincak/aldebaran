package com.aldebaran.chassis.monitoring.controller;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


@Component
@Path("/monitor")
public class MonitoringController {

    @GET
    @Path("/health")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHealth() {
        Map<String, Object> response = new HashMap<>();
        response.put("healthy", true);
        return Response
                .ok()
                .entity(response)
                .build();
    }

    @GET
    @Path("/metrics")
    @Produces({MediaType.TEXT_PLAIN,
               MediaType.APPLICATION_JSON})
    public Response getMetrics() throws IOException {
        StringWriter stringWriter = new StringWriter();
        TextFormat.write004(stringWriter,
                            CollectorRegistry.defaultRegistry.metricFamilySamples());
        return Response
                .ok(stringWriter.toString())
                .build();
    }
}
