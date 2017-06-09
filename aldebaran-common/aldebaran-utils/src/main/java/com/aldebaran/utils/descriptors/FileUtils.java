package com.aldebaran.utils.descriptors;

import java.util.UUID;


public class FileUtils {

    // TODO rethink
    public static String getExtension(String fileName) {
        if(fileName == null || fileName.isEmpty()) {
            return "";
        }
        String[] splitFilename = fileName.split("\\.");
        if(splitFilename.length == 0) {
            return "";
        }
        return splitFilename[splitFilename.length-1];
    }

    // TODO add checks
    public static String generateName(String extension) {
        return UUID.randomUUID().toString() + extension;
    }
}
