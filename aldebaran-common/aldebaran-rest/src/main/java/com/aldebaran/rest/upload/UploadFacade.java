package com.aldebaran.rest.upload;

import java.io.InputStream;


public interface UploadFacade {

    UploadedFile upload(InputStream inputStream, String fileName);
}