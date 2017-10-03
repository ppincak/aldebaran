package com.aldebaran.auth.api.controller

import com.aldebaran.auth.api.service.OAuth2Service
import com.aldebaran.auth.core.model.TokenRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Component
@Path("/oauth2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
open class OAuth2Controller
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
    fun tokenInfo(@HeaderParam("Authorization") authorizationHeader: String) : Response {
        return Response
                .ok(oauthService.tokenInfo(authorizationHeader))
                .build()
    }
}