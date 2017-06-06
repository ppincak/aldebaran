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
}