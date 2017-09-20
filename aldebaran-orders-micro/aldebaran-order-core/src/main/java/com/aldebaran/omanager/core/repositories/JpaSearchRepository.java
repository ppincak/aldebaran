package com.aldebaran.omanager.core.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;


@NoRepositoryBean
public interface JpaSearchRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    Page<T> findAll(Specification<T> specification, Pageable pageable);
}