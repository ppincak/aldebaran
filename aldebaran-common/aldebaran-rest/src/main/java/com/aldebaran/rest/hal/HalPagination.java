package com.aldebaran.rest.hal;

import com.aldebaran.rest.search.PaginationResponse;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.UriTemplate;

import java.util.HashMap;
import java.util.Map;


public class HalPagination {

    public static Map<String, Object> assembleParams(Integer page, Integer limit, String orderBy, String orderDirection) {
        Map<String, Object> params = new HashMap<>();
        if(page != null) {
            params.put("page", page);
        }
        if(limit != null) {
            params.put("limit", limit);
        }
        if(orderBy != null) {
            params.put("orderBy", orderBy);
        }
        if(orderDirection != null) {
            params.put("orderDirection", orderDirection);
        }
        return params;
    }

    public static <T> boolean hasNextPage(PaginationResponse<T> paginationResponse) {
        return paginationResponse.getPage() != null &&
                paginationResponse.getTotalPages() > paginationResponse.getPage();
    }

    public static <T> boolean hasPrevPage(PaginationResponse<T> paginationResponse) {
        return paginationResponse.getPage() != null && paginationResponse.getPage() > 0;
    }

    private static final String PAGINATION_TEMPLATE = "{?page,limit,orderBy,orderDirection}";

    public static <T> PaginationResponse<T> toPagination(String path, PaginationResponse<T> paginationResponse) {
        Integer page = paginationResponse.getPage();
        Integer limit = paginationResponse.getLimit();
        String orderBy = paginationResponse.getOrderBy();
        String orderDirection = paginationResponse.getOrderDirection();

        // NOTE(all) strip trailing slash
        UriTemplate uriTemplate = new UriTemplate(path + PAGINATION_TEMPLATE);

        paginationResponse.add(
            new Link(uriTemplate.expand(assembleParams(page, limit, orderBy, orderDirection)).toString(),
                     Link.REL_SELF));

        if(HalPagination.hasPrevPage(paginationResponse)) {
            paginationResponse.add(
                new Link(uriTemplate.expand(assembleParams(page - 1, limit, orderBy, orderDirection)).toString(),
                         Link.REL_PREVIOUS));

        }

        if(HalPagination.hasNextPage(paginationResponse)) {
            paginationResponse.add(
                new Link(uriTemplate.expand(assembleParams(page + 1, limit, orderBy, orderDirection)).toString(),
                            Link.REL_NEXT));
        }

        paginationResponse.embedResource("items", paginationResponse.getData());
        paginationResponse.setData(null);
        return paginationResponse;
    }
}
