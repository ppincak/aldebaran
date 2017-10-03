package com.aldebaran.utils.descriptor;


public interface MatchingEnum<T extends Enum<T>> {

    String[] getRepresentations();
}
