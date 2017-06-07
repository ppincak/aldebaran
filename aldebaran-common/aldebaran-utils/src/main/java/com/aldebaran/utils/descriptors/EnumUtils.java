package com.aldebaran.utils.descriptors;


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
        for(T labelable: enumClass.getEnumConstants()) {
            if(labelable.getValue().equals(value)) {
                return labelable;
            }
        }
        return null;
    }
}