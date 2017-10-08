package com.aldebaran.order.api.services;

import com.aldebaran.order.core.ApiProperties;
import com.aldebaran.order.core.assemblers.FileLinkAssembler;
import com.aldebaran.order.core.entities.FileLink;
import com.aldebaran.order.core.entities.FileType;
import com.aldebaran.order.core.model.FileLinkResponse;
import com.aldebaran.order.core.repositories.FileLinkRepository;
import com.aldebaran.rest.error.GeneralErrorEvents;
import com.aldebaran.rest.error.event.ApplicationException;
import com.aldebaran.rest.files.DownloadableFile;
import com.aldebaran.rest.files.FileStorageFacade;
import com.aldebaran.rest.files.UploadedFile;
import com.aldebaran.utils.FileUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


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
    public FileLink getFileLink(Long fileId) {
        FileLink fileLink = fileLinkRepository.findOne(fileId);
        if(fileLink == null) {
            throw new ApplicationException(GeneralErrorEvents.RESOURCE_NOT_FOUND);
        }
        return fileLink;
    }

    // TODO refactor
    @Override
    public List<FileLinkResponse> uploadFile(String fileName, List<FormDataBodyPart> formDataBodyParts) {
        List<FileLink> fileLinks = new ArrayList<>();

        for(FormDataBodyPart bodyPart: formDataBodyParts) {
            FormDataContentDisposition contentDisposition =
                    bodyPart.getFormDataContentDisposition();

            String extension = "." +
                    FileUtils.getExtension(contentDisposition.getFileName());

            UploadedFile uploadedFile;
            try {
                InputStream inputStream = bodyPart.getEntityAs(InputStream.class);

                uploadedFile = fileStorageFacade.upload(inputStream,
                                                        FileUtils.generateName(extension));
            } catch (Exception e) {
                continue;
            }
            if(formDataBodyParts.size() > 1) {
                fileName = contentDisposition.getFileName();
            }

            MediaType mediaType = bodyPart.getMediaType();

            FileLink fileLink = new FileLink();
            fileLink.setFilename(fileName);
            fileLink.setFileType(FileType.IMAGE);
            fileLink.setFileLength(uploadedFile.getFileLength());
            fileLink.setUrl(uploadedFile.getFileUrl());
            fileLink.setMediaType(mediaType.getType() + "/" +
                                  mediaType.getSubtype());
            fileLinks.add(fileLink);
        }

        if(fileLinks.isEmpty() == false) {
            try {
                fileLinkRepository.save(fileLinks);
            } catch (Exception e) {
                for(FileLink fileLink: fileLinks) {
                    fileStorageFacade.delete(fileLink.getUrl());
                }
            }
        }

        return fileLinkAssembler.toResponse(fileLinks);
    }

    @Override
    public DownloadableFile downloadFile(Long fileId) {
        FileLink fileLink = getFileLink(fileId);

        StreamingOutput output =
                outputStream -> fileStorageFacade.download(fileLink.getUrl(), outputStream);

        return new DownloadableFile()
                    .setFilename(fileLink.getFilename())
                    .setMediaType(fileLink.getMediaType())
                    .setLength(fileLink.getFileLength())
                    .setStreamingOutput(output);
    }

    @Override
    public void deleteFile(Long fileId) {
        FileLink fileLink = getFileLink(fileId);
        fileStorageFacade.delete(fileLink.getUrl());
        fileLinkRepository.delete(fileLink);
    }
}