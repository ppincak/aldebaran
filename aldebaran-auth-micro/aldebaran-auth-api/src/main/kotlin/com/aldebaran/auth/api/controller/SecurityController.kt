package com.aldebaran.auth.api.controller

import com.aldebaran.auth.api.service.SecurityService
import com.aldebaran.auth.core.model.TokenRevokeRequest
import com.aldebaran.rest.interceptors.ApplyDeviceContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.validation.Valid
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Component
@Path("/security")
@ApplyDeviceContext
open class SecurityController
        @Autowired constructor(val securityService: SecurityService) {

    @POST
    @Path("/token/revoke")
    @Produces(MediaType.APPLICATION_JSON)
    fun revoke(@Valid tokenRevokeRequest: TokenRevokeRequest) : Response {
        return Response
                .ok(securityService.revoke(tokenRevokeRequest))
                .build()
    }
}