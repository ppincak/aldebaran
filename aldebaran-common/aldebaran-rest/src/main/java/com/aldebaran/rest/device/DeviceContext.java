package com.aldebaran.rest.device;

import java.util.Locale;


public final class DeviceContext {

    public static final String DEFAULT_LANG = "en";

    public static final Locale DEFAULT_LOCALE =
            new Locale("en");

    private static final ThreadLocal<DeviceContext> context =
            new ThreadLocal<>();

    public static void setContext(DeviceContext deviceContext) {
        context.set(deviceContext);
    }

    public static DeviceContext getContext() {
        return context.get();
    }

    private final String lang;
    private final Locale locale;
    private final String apiKey;

    public DeviceContext(String lang, String apiKey) {
        this.lang = parseLang(lang);
        this.locale = parseLocale(this.lang);
        this.apiKey = apiKey;
    }

    private static String parseLang(String lang) {
        return lang == null || lang.isEmpty() ? DEFAULT_LANG : lang;
    }

    private static Locale parseLocale(String lang) {
        try {
            return new Locale(lang);
        } catch (Exception e) {
            return DEFAULT_LOCALE;
        }
    }

    public String getLang() {
        return lang;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getApiKey() {
        return apiKey;
    }
}