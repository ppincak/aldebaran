package com.aldebaran.utils.descriptors;


import java.util.UUID;

public class FileUtils {

    public static String getExtension(String fileName) {
        if(fileName == null || fileName.isEmpty()) {
            return "";
        }
        String[] splitFilename = fileName.split(".");
        return splitFilename[splitFilename.length-1];
    }

    // TODO add checks
    public static String generateName(String extension) {
        return UUID.randomUUID().toString() + extension;
    }
}
