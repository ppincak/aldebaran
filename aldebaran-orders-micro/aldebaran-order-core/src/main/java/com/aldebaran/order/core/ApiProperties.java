package com.aldebaran.order.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ApiProperties {

    @Value("${api.defaultPage}")
    private Integer defaultPage;

    @Value("${api.defaultPageSize}")
    private Integer defaultPageSize;

    @Value("${api.maxFileLength}")
    private Integer maxFileLength;

    public Integer getDefaultPage() {
        return defaultPage;
    }

    public void setDefaultPage(Integer defaultPage) {
        this.defaultPage = defaultPage;
    }

    public Integer getDefaultPageSize() {
        return defaultPageSize;
    }

    public void setDefaultPageSize(Integer defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

    public Integer getMaxFileLength() {
        return maxFileLength;
    }

    public void setMaxFileLength(Integer maxFileLength) {
        this.maxFileLength = maxFileLength;
    }
}
