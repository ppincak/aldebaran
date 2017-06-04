package com.aldebaran.omanager.core.entities;

import com.aldebaran.data.domain.TrackableBaseDomain;

import javax.persistence.*;
import javax.ws.rs.Consumes;


@Entity
@Table(name = FileLink.tableName)
public class FileLink extends TrackableBaseDomain {

    public static final String tableName = "file_link";

    @Column(name = "`name`")
    private String name;

    @Column(name = "file_link_type",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private FileLinkType fileLinkType;

    @Column(name = "url",
            nullable = false)
    private String url;

    @Column(name = "metadata")
    private String metadata;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getTableName() {
        return tableName;
    }

    public FileLinkType getFileLinkType() {
        return fileLinkType;
    }

    public void setFileLinkType(FileLinkType fileLinkType) {
        this.fileLinkType = fileLinkType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}