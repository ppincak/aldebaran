package com.aldebaran.utils;

import com.aldebaran.utils.descriptor.LabelEnum;
import com.aldebaran.utils.descriptor.MatchingEnum;
import com.aldebaran.utils.descriptor.ValueEnum;


//NOTE(peter.pincak) rethink this
public final class EnumUtils {

    public static <T extends Enum<T> & LabelEnum> T getByLabel(Class<T> enumClass, String label) {
        for(T labelableEnum: enumClass.getEnumConstants()) {
            if(labelableEnum.getLabel().equals(label)) {
                return labelableEnum;
            }
        }
        return null;
    }

    public static <T extends Enum<T> & ValueEnum> T getByValue(Class<T> enumClass, Object value) {
        for(T enumValue: enumClass.getEnumConstants()) {
            if(enumValue.getValue().equals(value)) {
                return enumValue;
            }
        }
        return null;
    }

    public static <T extends Enum<T> & MatchingEnum> T getByRepresentation(Class<T> enumClass, String representation) {
        for(T enumValue: enumClass.getEnumConstants()) {
            for(String repre: enumValue.getRepresentations()) {
                if(repre.equalsIgnoreCase(representation)) {
                    return enumValue;
                }
            }
        }
        return null;
    }
}