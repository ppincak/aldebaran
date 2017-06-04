package com.aldebaran.omanager.core;

import com.aldebaran.rest.upload.FileStorageFacade;
import com.aldebaran.rest.upload.FileUploadException;
import com.aldebaran.rest.upload.UploadedFile;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Component
public class LocalFileStorage implements FileStorageFacade {

    @Override
    public UploadedFile upload(InputStream inputStream, String filePath) {
        try {
            long size = Files.copy(inputStream, Paths.get(filePath));
            // TODO check if this condition needs to be  here
            if(size <= 0) {
                throw new FileUploadException();
            }
        } catch (IOException e) {
            throw new FileUploadException();
        }
        return new UploadedFile(filePath, "");
    }

    @Override
    public long download(String filePath, OutputStream outputStream) {
        try {
            return Files.copy(Paths.get(filePath), outputStream);
        } catch (IOException e) {
            throw new FileUploadException();
        }
    }
}