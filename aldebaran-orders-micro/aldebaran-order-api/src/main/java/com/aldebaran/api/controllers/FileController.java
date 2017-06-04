package com.aldebaran.api.controllers;

import com.aldebaran.api.services.FileService;
import com.aldebaran.rest.upload.DownloadableFile;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Component
@Path("/files")
@Produces({MediaType.APPLICATION_JSON})
public class FileController {

    @Autowired
    private FileService fileService;

    @POST
    @Path("/{fileName}")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response uploadFile(@PathParam("fileName") String fileName,
                               @FormDataParam("file") final List<FormDataBodyPart> formDataBodyParts) {
        return Response
                .status(Response.Status.CREATED)
                .entity(fileService.uploadFile(fileName, formDataBodyParts))
                .build();
    }

    @GET
    @Path("/{fileId}")
    public Response downloadFile(@PathParam("fileId") Long fileId,
                                 @Context HttpServletResponse response) throws Exception {
        DownloadableFile downloadableFile = fileService.downloadFile(fileId, response.getOutputStream());
        response.setContentLength((int) downloadableFile.getLength());
        response.setHeader("Content-Disposition", "attachment; filename="
                + downloadableFile.getFilename());
        return Response
                .ok()
                .build();
    }

    @DELETE
    @Path("/{fileId}")
    public Response deleteFile(@PathParam("fileId") Long fileId) {
        fileService.deleteFile(fileId);
        return Response
                .noContent()
                .build();
    }
}