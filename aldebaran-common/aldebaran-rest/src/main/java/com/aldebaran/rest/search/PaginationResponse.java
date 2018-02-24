package com.aldebaran.rest.search;

import com.aldebaran.rest.hal.HalResourceCollection;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;


public class PaginationResponse<T> extends HalResourceCollection<T> {

    @JsonProperty
    private Integer totalPages;

    @JsonProperty
    private Integer totalElements;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer page;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer limit;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String orderBy;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String orderDirection;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public static<T> PaginationBuilder<T> data(Collection<T> data) {
        return new PaginationBuilder<>(data);
    }

    public static class PaginationBuilder<T> {
        private Integer totalPages;
        private Integer totalElements;
        private PaginationRequest paginationRequest;
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

        public PaginationBuilder<T> pagination(PaginationRequest paginationRequest) {
            this.paginationRequest = paginationRequest;
            return this;
        }

        public PaginationResponse<T> build() {
            PaginationResponse<T> paginationResponse = new PaginationResponse<>();
            paginationResponse.setData(data);
            paginationResponse.setTotalPages(totalPages);
            paginationResponse.setTotalElements(totalElements);
            if(paginationRequest != null) {
                paginationResponse.setPage(paginationRequest.getPage());
                paginationResponse.setLimit(paginationRequest.getLimit());
                paginationResponse.setOrderBy(paginationRequest.getOrderBy());
                paginationResponse.setOrderDirection(paginationRequest.getOrderDirection());
            }
            return paginationResponse;
        }
    }
}