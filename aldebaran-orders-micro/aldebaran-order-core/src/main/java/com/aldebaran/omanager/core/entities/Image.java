package com.aldebaran.omanager.core.entities;

import com.aldebaran.data.domain.TrackableBaseDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = Image.tableName)
public class Image extends TrackableBaseDomain {

    public static final String tableName = "image";

    @Column(name = "url",
            nullable = false)
    private String url;

    @Column(name = "metadata")
    private String metadata;

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