package com.aldebaran.omanager.core.entities;

import com.aldebaran.data.domain.TrackableBaseDomain;

import javax.persistence.*;


@Entity
@Table(name = FileLink.tableName)
public class FileLink extends TrackableBaseDomain {

    public static final String tableName = "file_link";

    @Column(name = "file_name",
            nullable = false)
    private String filename;

    @Column(name = "file_length",
            nullable = false)
    private Long fileLength;

    @Column(name = "file_link_type",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @Column(name = "media_type",
            nullable = false)
    private String mediaType;

    @Column(name = "url",
            nullable = false,
            unique = true)
    private String url;
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getFileLength() {
        return fileLength;
    }

    public void setFileLength(Long fileLength) {
        this.fileLength = fileLength;
    }

    public static String getTableName() {
        return tableName;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}