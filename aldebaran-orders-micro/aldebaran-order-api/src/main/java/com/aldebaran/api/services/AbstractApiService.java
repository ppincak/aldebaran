package com.aldebaran.api.services;

import com.aldebaran.omanager.core.ApiProperties;
import com.aldebaran.omanager.core.Utils;
import com.aldebaran.omanager.core.descriptors.CustomerSearchDescriptors;
import com.aldebaran.rest.error.GeneralErrorEvents;
import com.aldebaran.rest.error.codes.ApplicationException;
import com.aldebaran.rest.search.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


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
                                              Set<String> orderProperties) {

        int page = paginationRequest.getPage() != null ?  paginationRequest.getPage() :
                                                          apiProperties.getDefaultPage();

        int limit = paginationRequest.getLimit() != null ? paginationRequest.getLimit() :
                                                           apiProperties.getDefaultPageSize();
        Sort.Direction orderDirection =
                paginationRequest.orderDirection() != null ? paginationRequest.orderDirection() :
                                                             Sort.Direction.ASC;

        String[] orderBy = Utils.orderBy(orderProperties, paginationRequest.orderProperties());
        if(orderBy.length == 0 && orderProperties.contains("id")) {
            orderBy = new String[] {"id"};
        }

        return new PageRequest(page,
                               limit,
                               orderDirection,
                               orderBy);
    }
}