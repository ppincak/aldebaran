package com.aldebaran.api.services;

import com.aldebaran.omanager.core.ApiProperties;
import com.aldebaran.omanager.core.assemblers.FileLinkAssembler;
import com.aldebaran.omanager.core.entities.FileLink;
import com.aldebaran.omanager.core.entities.FileLinkType;
import com.aldebaran.omanager.core.model.FileLinkResponse;
import com.aldebaran.omanager.core.repositories.FileLinkRepository;
import com.aldebaran.rest.error.GeneralErrorCodes;
import com.aldebaran.rest.error.codes.ApplicationException;
import com.aldebaran.rest.upload.DownloadableFile;
import com.aldebaran.rest.upload.FileStorageFacade;
import com.aldebaran.rest.upload.UploadedFile;
import com.aldebaran.utils.descriptors.FileUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


// TODO add unique constraint to ProductFileLink
// TODO add length to file
// TODO set correct file length
@Service
@Transactional
public class FileServiceImpl implements FileService {

    @Autowired
    private ApiProperties apiProperties;

    @Autowired
    private FileLinkRepository fileLinkRepository;

    @Autowired
    private FileLinkAssembler fileLinkAssembler;

    @Autowired
    private FileStorageFacade fileStorageFacade;

    @Override
    public List<FileLinkResponse> uploadFile(String fileName, List<FormDataBodyPart> formDataBodyParts) {
        List<FileLink> fileLinks = new ArrayList<>();

        for(FormDataBodyPart bodyPart: formDataBodyParts) {
            FormDataContentDisposition contentDisposition =
                    bodyPart.getFormDataContentDisposition();

            UploadedFile uploadedFile;
            try {
                InputStream inputStream = bodyPart.getEntityAs(InputStream.class);

                uploadedFile = fileStorageFacade.upload(inputStream,
                                                        FileUtils.generateName(".jpg"));
            } catch (Exception e) {
                continue;
            }
            if(formDataBodyParts.size() > 1) {
                fileName = contentDisposition.getFileName();
            }

            FileLink fileLink = new FileLink();
            fileLink.setName(fileName);
            fileLink.setFileLinkType(FileLinkType.IMAGE);
            fileLink.setUrl(uploadedFile.getFileUrl());
            fileLinks.add(fileLink);
        }

        if(fileLinks.isEmpty() == false) {
            fileLinkRepository.save(fileLinks);
        }

        return fileLinkAssembler.toResponse(fileLinks);
    }

    @Override
    public DownloadableFile downloadFile(Long fileId, OutputStream outputStream) {
        FileLink fileLink = fileLinkRepository.findOne(fileId);
        if(fileLink == null) {
            throw new ApplicationException(GeneralErrorCodes.RESOURCE_NOT_FOUND);
        }
        long length = fileStorageFacade.download(fileLink.getUrl(), outputStream);
        return new DownloadableFile(length, fileLink.getName());
    }

    @Override
    public void deleteFile(Long fileId) {

    }
}