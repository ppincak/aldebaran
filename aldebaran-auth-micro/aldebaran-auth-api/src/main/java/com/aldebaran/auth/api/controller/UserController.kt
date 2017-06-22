package com.aldebaran.auth.api.controller

import com.aldebaran.auth.api.service.UserService
import com.aldebaran.auth.core.model.UserRegistrationRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Component
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
open class UserController
        @Autowired constructor(val userService: UserService) {

    @POST
    @Path("/register")
    fun register(@Valid userRegistrationRequest: UserRegistrationRequest) : Response {
        userService.register(userRegistrationRequest)
        return Response
                .status(Response.Status.CREATED)
                .build()
    }
}