package com.aldebaran.api.services;


import com.aldebaran.omanager.core.model.FileLinkResponse;
import com.aldebaran.rest.files.DownloadableFile;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import java.util.List;


public interface FileService {

    List<FileLinkResponse> uploadFile(String fileName,
                                      List<FormDataBodyPart> formDataBodyParts);

    DownloadableFile downloadFile(Long fileId);

    void deleteFile(Long fileId);
}