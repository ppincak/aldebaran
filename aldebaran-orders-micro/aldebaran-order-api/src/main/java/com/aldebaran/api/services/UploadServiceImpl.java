package com.aldebaran.api.services;

import com.aldebaran.omanager.core.ApiProperties;
import com.aldebaran.omanager.core.assemblers.FileLinkAssembler;
import com.aldebaran.omanager.core.entities.FileLink;
import com.aldebaran.omanager.core.entities.FileLinkType;
import com.aldebaran.omanager.core.model.FileLinkResponse;
import com.aldebaran.omanager.core.repositories.FileLinkRepository;
import com.aldebaran.rest.upload.UploadFacade;
import com.aldebaran.rest.upload.UploadedFile;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class UploadServiceImpl implements UploadService {

    @Autowired
    private ApiProperties apiProperties;

    @Autowired
    private FileLinkRepository fileLinkRepository;

    @Autowired
    private FileLinkAssembler fileLinkAssembler;

    @Autowired
    private UploadFacade uploadFacade;

    @Override
    public FileLinkResponse uploadFile(String fileName, List<FormDataBodyPart> formDataBodyParts) {
        List<FileLink> fileLinks = new ArrayList<>();

        try {
            for(FormDataBodyPart bodyPart: formDataBodyParts) {
                FormDataContentDisposition contentDisposition = bodyPart.getFormDataContentDisposition();
                InputStream inputStream = bodyPart.getEntityAs(InputStream.class);
                UploadedFile uploadedFile = uploadFacade.upload(inputStream, "");

                FileLink fileLink = new FileLink();
                fileLink.setName(fileName);
                fileLink.setFileLinkType(FileLinkType.IMAGE);
                fileLink.setUrl(uploadedFile.getFileUrl());
                fileLinks.add(fileLink);
            }
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public void deleteFile(Long fileId) {

    }

    public String generateName() {
        return UUID.randomUUID().toString();
    }
}