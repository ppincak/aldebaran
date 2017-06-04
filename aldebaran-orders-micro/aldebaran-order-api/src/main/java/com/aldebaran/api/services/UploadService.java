package com.aldebaran.api.services;


import com.aldebaran.omanager.core.model.FileLinkResponse;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import java.util.List;


public interface UploadService {

    FileLinkResponse uploadFile(String fileName,
                                List<FormDataBodyPart> formDataBodyParts);

    void deleteFile(Long fileId);
}