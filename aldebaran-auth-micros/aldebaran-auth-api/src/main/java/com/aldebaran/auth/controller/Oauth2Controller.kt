package com.aldebaran.auth.controller

import com.aldebaran.auth.JwtProperties
import com.aldebaran.auth.model.TokenRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Component
@Path("/oauth2")
@Consumes(MediaType.APPLICATION_XML,
          MediaType.APPLICATION_JSON,
          MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
open class Oauth2Controller {

    @GET
    @Path("/token")
    fun test() : Response {
        return Response.ok().build()
    }
}