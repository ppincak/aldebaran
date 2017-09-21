package com.aldebaran.api.services;

import com.aldebaran.aql.OrderByClause;
import com.aldebaran.omanager.core.ApiProperties;
import com.aldebaran.rest.RestUtils;
import com.aldebaran.rest.error.GeneralErrorEvents;
import com.aldebaran.rest.error.event.ApplicationException;
import com.aldebaran.rest.search.OrderDescriptor;
import com.aldebaran.rest.search.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;


public abstract class AbstractApiService<TRepository extends JpaRepository<TEntity, Long>, TEntity> {

    @Autowired
    protected ApiProperties apiProperties;

    protected final TRepository repository;

    public AbstractApiService(TRepository repository) {
        this.repository = repository;
    }

    protected TEntity getById(Long id) {
        TEntity entity = repository.findOne(id);
        if(entity == null) {
            throw new ApplicationException(GeneralErrorEvents.RESOURCE_NOT_FOUND);
        }
        return entity;
    }

    protected PageRequest assemblePageRequest(PaginationRequest paginationRequest,
                                              Map<String, OrderDescriptor> orderDescriptors) {

        int page = paginationRequest.getPage() != null ?  paginationRequest.getPage() :
                                                          apiProperties.getDefaultPage();

        int limit = paginationRequest.getLimit() != null ? paginationRequest.getLimit() :
                                                           apiProperties.getDefaultPageSize();

        Sort.Direction orderDirection =
                paginationRequest.orderDirection() != null ? paginationRequest.orderDirection() :
                                                             Sort.Direction.ASC;

        String[] orderBy = RestUtils.orderBy(paginationRequest.orderProperties(),
                                             orderDescriptors);
        if(orderBy.length == 0) {
            orderBy = new String[] {"id"};
        }

        return new PageRequest(page, limit, orderDirection, orderBy);
    }
}