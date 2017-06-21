package com.aldebaran.omanager.core;

import com.aldebaran.rest.files.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


@Component
public class LocalFileStorage implements FileStorageFacade {

    @Override
    public UploadedFile upload(InputStream inputStream, String filePath) {
        long size;

        try {
            size = Files.copy(inputStream, Paths.get(filePath));
        } catch (IOException e) {
            throw new FileUploadException();
        }
        return new UploadedFile(filePath, "", size);
    }

    @Override
    public long download(String filePath, OutputStream outputStream) {
        try {
            return Files.copy(Paths.get(filePath), outputStream);
        } catch (IOException e) {
            throw new FileDownloadException();
        }
    }

    @Override
    public void delete(String filePath) {
        try {
            Files.delete(Paths.get(filePath));
        } catch (IOException e) {
            throw new FileDeleteException();
        }
    }
}