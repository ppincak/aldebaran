package com.aldebaran.auth.controller

import com.aldebaran.auth.core.model.TokenRequest
import com.aldebaran.auth.service.OAuth2Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Component
@Path("/oauth2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
open class Oauth2Controller
    @Autowired constructor(val oauthService: OAuth2Service) {

    @POST
    @Path("/token")
    fun token(tokenRequest: TokenRequest) : Response {
        return Response
                .ok(oauthService.token(tokenRequest))
                .build()
    }

    @GET
    @Path("/tokenInfo")
    fun tokenInfo(@HeaderParam("Authorization") jwt: String) : Response {
        return Response
                .ok(oauthService.tokenInfo(jwt))
                .build()
    }
}