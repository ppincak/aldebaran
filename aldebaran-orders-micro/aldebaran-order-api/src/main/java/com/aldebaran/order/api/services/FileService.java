package com.aldebaran.order.api.services;


import com.aldebaran.order.core.entities.FileLink;
import com.aldebaran.order.core.model.FileLinkResponse;
import com.aldebaran.rest.files.DownloadableFile;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import java.util.List;


public interface FileService {

    FileLink getFileLink(Long fileId);

    List<FileLinkResponse> uploadFile(String fileName,
                                      List<FormDataBodyPart> formDataBodyParts);

    DownloadableFile downloadFile(Long fileId);

    void deleteFile(Long fileId);
}