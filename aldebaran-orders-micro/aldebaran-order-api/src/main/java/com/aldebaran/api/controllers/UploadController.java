package com.aldebaran.api.controllers;

import com.aldebaran.api.services.UploadService;
import com.aldebaran.omanager.core.repositories.FileLinkRepository;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;


@Component
@Path("/upload")
@Produces({MediaType.APPLICATION_JSON})
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @POST
    @Path("/{fileName}")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response uploadFile(@PathParam("fileName") String fileName,
                               @FormDataParam("file") final List<FormDataBodyPart> formDataBodyParts) {
        return Response
                .status(Response.Status.CREATED)
                .entity(uploadService.uploadFile(fileName, formDataBodyParts))
                .build();
    }

    @GET
    @Path("/{fileLinkId}")
    public Response downloadFile(@PathParam("fileLinkId") Long fileLinkId) {
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

    @DELETE
    @Path("/{fileId}")
    public Response deleteFile(@PathParam("fileId") Long fileId) {
        uploadService.deleteFile(fileId);
        return Response
                .noContent()
                .build();
    }
}