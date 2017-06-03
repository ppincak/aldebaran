package com.aldebaran.rest.search;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;


public class PaginationResponse<T> {

    @JsonProperty
    private Integer totalPages;

    @JsonProperty
    private Integer totalElements;

    @JsonProperty
    private Collection<T> data;

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public static<T> PaginationBuilder<T> data(Collection<T> data) {
        return new PaginationBuilder<>(data);
    }

    public static class PaginationBuilder<T> {
        private Integer totalPages;
        private Integer totalElements;
        private Collection<T> data;

        PaginationBuilder(Collection<T> data) {
            this.data = data;
        }

        public PaginationBuilder<T> totalPages(Integer totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public PaginationBuilder<T> totalElements(Integer totalElements) {
            this.totalElements= totalElements;
            return this;
        }

        public PaginationResponse<T> build() {
            PaginationResponse<T> paginationResponse = new PaginationResponse<>();
            paginationResponse.setData(data);
            paginationResponse.setTotalElements(totalElements);
            paginationResponse.setTotalPages(totalPages);
            return paginationResponse;
        }
    }
}