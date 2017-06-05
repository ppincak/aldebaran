package com.aldebaran.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Component
@Path("/oauth2/test")
open class Oauth2Controller {

    @Autowired
    var jwtProperties: JwtProperties? = null
    set(value) {
        field = value
    }

    @Autowired
    var testProperties: TestProps? = null
        set(value) {
            field = value
        }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun test() : Response {
        return Response.ok(testProperties).build()
    }
}