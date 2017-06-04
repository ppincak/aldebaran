package com.aldebaran.omanager.core;

import com.aldebaran.rest.upload.UploadFacade;
import com.aldebaran.rest.upload.UploadedFile;
import org.springframework.stereotype.Component;

import java.io.InputStream;


@Component
public class LocalUpload implements UploadFacade {

    @Override
    public UploadedFile upload(InputStream inputStream, String fileName) {
        return null;
    }
}
