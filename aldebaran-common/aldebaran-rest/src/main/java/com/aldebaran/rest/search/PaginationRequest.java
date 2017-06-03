package com.aldebaran.rest.search;

import org.springframework.data.domain.Sort;

import javax.ws.rs.QueryParam;


public class PaginationRequest {

    private static final String SPLIT_CHAR = ",";

    @QueryParam("page")
    private Integer page;

    @QueryParam("limit")
    private Integer limit;

    @QueryParam("orderBy")
    private String orderBy;

    @QueryParam("orderDirection")
    private String orderDirection;

    public Sort.Direction orderDirection() {
        if(orderDirection == null || orderDirection.isEmpty()) {
            return Sort.Direction.ASC;
        }
        try {
            return Sort.Direction.valueOf(orderDirection.toUpperCase());
        } catch (Exception e) {
            return Sort.Direction.ASC;
        }
    }

    public String[] orderProperties() {
        return orderBy != null ? orderBy.split(SPLIT_CHAR) : new String[0];
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
}