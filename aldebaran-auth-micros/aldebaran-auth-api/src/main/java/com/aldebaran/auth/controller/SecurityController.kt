package com.aldebaran.auth.controller

import com.aldebaran.auth.service.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.ws.rs.HeaderParam
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Response


@Path("/security")
@Component
class SecurityController @Autowired constructor(val securityService: SecurityService) {

    @POST
    @Path("/token/revoke")
    fun revoke(@HeaderParam("Authorization") jwt: String) : Response {
        return Response
                .ok(securityService.revoke(jwt))
                .build()
    }
}