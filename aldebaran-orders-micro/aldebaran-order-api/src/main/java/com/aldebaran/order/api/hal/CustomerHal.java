package com.aldebaran.order.api.hal;

import com.aldebaran.order.api.controllers.CustomerController;
import com.aldebaran.order.core.model.CustomerResponse;
import com.aldebaran.rest.search.PaginationResponse;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;

import java.util.HashMap;
import java.util.Map;


public class CustomerHal {

    public static CustomerResponse addLinks(CustomerResponse customerResponse) {
        customerResponse
                .add(JaxRsLinkBuilder
                        .linkTo(CustomerController.class)
                        .slash(customerResponse.getCustomerId())
                        .withSelfRel());

        customerResponse
                .add(JaxRsLinkBuilder
                        .linkTo(CustomerController.class)
                        .slash(customerResponse.getCustomerId())
                        .slash("orders")
                        .withRel("orders"));

        return customerResponse;
    }

    private static final String PAGINATION_TEMPLATE = "{?page,limit,orderBy,orderDirection}";

    public static PaginationResponse<CustomerResponse> addLinks(PaginationResponse<CustomerResponse> paginationResponse) {
        for(CustomerResponse customerResponse: paginationResponse.getData()) {
            addLinks(customerResponse);
        }

        UriTemplate uriTemplate = new UriTemplate("/customers" + PAGINATION_TEMPLATE);

        Integer page = paginationResponse.getPage();
        Integer limit = paginationResponse.getLimit();
        String orderBy = paginationResponse.getOrderBy();
        String orderDirection = paginationResponse.getOrderDirection();

        paginationResponse.add(
                new Link(uriTemplate.expand(assembleParams(page, limit, orderBy, orderDirection)).toString(),
                        Link.REL_SELF));

        if(hasPrevPage(paginationResponse)) {
            paginationResponse.add(
                    new Link(uriTemplate.expand(assembleParams(page -1, limit, orderBy, orderDirection)).toString(),
                            Link.REL_PREVIOUS));

        }

        if(hasNextPage(paginationResponse)) {
            paginationResponse.add(
                new Link(uriTemplate.expand(assembleParams(page +1, limit, orderBy, orderDirection)).toString(),
                         Link.REL_NEXT));
        }

        paginationResponse.embedResource("items", paginationResponse.getData());
        paginationResponse.setData(null);
        return paginationResponse;
    }

    private static Map<String, Object> assembleParams(Integer page, Integer limit, String orderBy, String orderDirection) {
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

    private static boolean hasNextPage(PaginationResponse<CustomerResponse> paginationResponse) {
        return paginationResponse.getPage() != null &&
               paginationResponse.getTotalPages() > paginationResponse.getPage();
    }

    private static boolean hasPrevPage(PaginationResponse<CustomerResponse> paginationResponse) {
        return paginationResponse.getPage() != null && paginationResponse.getPage() > 0;
    }
}