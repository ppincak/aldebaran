package com.aldebaran.omanager.core.model;

import com.aldebaran.omanager.core.entities.FileType;
import com.fasterxml.jackson.annotation.JsonProperty;


public class FileLinkResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String filename;

    @JsonProperty
    private String url;

    @JsonProperty
    private String fileLength;

    @JsonProperty
    private FileType fileType;

    @JsonProperty
    private String mediaType;

    @JsonProperty
    private TimestampsModel timestamps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileLength() {
        return fileLength;
    }

    public void setFileLength(String fileLength) {
        this.fileLength = fileLength;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public TimestampsModel getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(TimestampsModel timestamps) {
        this.timestamps = timestamps;
    }
}
