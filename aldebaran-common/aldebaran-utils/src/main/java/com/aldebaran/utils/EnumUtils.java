package com.aldebaran.utils;


public class EnumUtils {

    public static <T extends Enum<T> & LabelEnum> T getByLabel(Class<T> enumClass, String label) {
        for(T labelable: enumClass.getEnumConstants()) {
            if(labelable.getLabel().equals(label)) {
                return labelable;
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
}