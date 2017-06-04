package com.aldebaran.api.controllers;

import com.aldebaran.api.services.FileService;
import com.aldebaran.rest.files.DownloadableFile;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Component
@Path("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @POST
    @Path("/{fileName}")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_JSON})
    public Response uploadFile(@PathParam("fileName") String fileName,
                               @FormDataParam("file") final List<FormDataBodyPart> formDataBodyParts) {

        return Response
                .status(Response.Status.CREATED)
                .entity(fileService.uploadFile(fileName, formDataBodyParts))
                .build();
    }

    @GET
    @Path("/{fileId}")
    public Response downloadFile(@PathParam("fileId") Long fileId) throws Exception {
        DownloadableFile downloadableFile =
                fileService.downloadFile(fileId);

        return Response
                .ok(downloadableFile.getStreamingOutput())
                .header("Content-Type", downloadableFile.getMediaType())
                .header("Content-Disposition", "attachment; filename=" + downloadableFile.getFilename())
                .build();
    }

    @DELETE
    @Path("/{fileId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteFile(@PathParam("fileId") Long fileId) {
        fileService.deleteFile(fileId);
        return Response
                .noContent()
                .build();
    }
}