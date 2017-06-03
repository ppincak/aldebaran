package com.aldebaran.data.message;

import java.util.Locale;


public interface MessageProvider {

    String getMessage(String code, Object[] args, Locale locale);
}
