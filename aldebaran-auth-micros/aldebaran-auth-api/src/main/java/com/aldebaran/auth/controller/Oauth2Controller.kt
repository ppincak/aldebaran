package com.aldebaran.auth.controller

import com.aldebaran.auth.core.model.TokenRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Component
@Path("/oauth2")
@Consumes(MediaType.APPLICATION_XML,
          MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
open class Oauth2Controller {

    @Autowired
    var oauthService: OAuth2Service? = null
    set(value) {
        field = value
    }

    @POST
    @Path("/token")
    fun token(tokenRequest: TokenRequest) : Response {
        return Response
                .ok()
                .build()
    }

    @GET
    @Path("/tokenInfo")
    fun tokenInfo() : Response {
        return Response
                .ok()
                .build()
    }
}