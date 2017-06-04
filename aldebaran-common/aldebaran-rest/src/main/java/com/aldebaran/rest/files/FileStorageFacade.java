package com.aldebaran.rest.files;

import java.io.InputStream;
import java.io.OutputStream;


public interface FileStorageFacade {

    UploadedFile upload(InputStream inputStream, String filePath);

    long download(String filePath, OutputStream outputStream);

    void delete(String filePath);
}