package com.aldebaran.rest.files;

import javax.ws.rs.core.StreamingOutput;


public class DownloadableFile {

    private long length;
    private String mediaType;
    private String filename;
    private StreamingOutput streamingOutput;

    public DownloadableFile() {
    }

    public long getLength() {
        return length;
    }

    public DownloadableFile setLength(long length) {
        this.length = length;
        return this;
    }

    public String getMediaType() {
        return mediaType;
    }

    public DownloadableFile setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public DownloadableFile setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public StreamingOutput getStreamingOutput() {
        return streamingOutput;
    }

    public DownloadableFile setStreamingOutput(StreamingOutput streamingOutput) {
        this.streamingOutput = streamingOutput;
        return this;
    }
}
