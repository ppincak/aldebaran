package com.aldebaran.rest.files;


public class UploadedFile {

    private String fileUrl;
    private String filename;
    private long fileLength;

    public UploadedFile(String fileUrl, String filename, long fileLength) {
        this.fileUrl = fileUrl;
        this.filename = filename;
        this.fileLength = fileLength;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }
}