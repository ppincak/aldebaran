package com.aldebaran.api.services;

import com.aldebaran.rest.error.GeneralErrorCodes;
import com.aldebaran.rest.error.codes.ApplicationException;
import org.springframework.data.jpa.repository.JpaRepository;


public abstract class AbstractApiService<TRepository extends JpaRepository<TEntity, Long>, TEntity> {

    protected final TRepository repository;

    public AbstractApiService(TRepository repository) {
        this.repository = repository;
    }

    protected TEntity getById(Long id) {
        TEntity entity = repository.findOne(id);
        if(entity == null) {
            throw new ApplicationException(GeneralErrorCodes.RESOURCE_NOT_FOUND);
        }
        return entity;
    }
}