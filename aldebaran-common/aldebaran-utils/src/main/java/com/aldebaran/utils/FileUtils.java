package com.aldebaran.utils;

import java.util.UUID;


public final class FileUtils {

    //NOTE(peter.pincak) rethink
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

    //NOTE(peter.pincak) rethink
    public static String generateName(String extension) {
        return UUID.randomUUID().toString() + extension;
    }
}
