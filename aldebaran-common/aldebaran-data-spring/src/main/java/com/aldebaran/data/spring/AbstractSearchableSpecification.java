package com.aldebaran.data.spring;

import com.aldebaran.utils.Searchable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;


public abstract class AbstractSearchableSpecification<T, Y extends Comparable<Y>> implements Specification<T> {

    private final Searchable<Y> searchable;

    protected AbstractSearchableSpecification(Searchable<Y> searchable) {
        this.searchable = searchable;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (searchable.getOperator()) {
            case EQUALS:
                return builder.equal(root.get(searchable.getSearchProperty()),
                                              searchable.getSearchValue());
            case NOT_EQUALS:
                return builder.notEqual(root.get(searchable.getSearchProperty()),
                                                 searchable.getSearchValue());
            case LESS_THAN:
                return builder.lessThan(root.get(searchable.getSearchProperty()),
                                                 searchable.getSearchValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(searchable.getSearchProperty()),
                                                    searchable.getSearchValue());
            case LESS_THAN_EQUALS:
                return builder.lessThanOrEqualTo(root.get(searchable.getSearchProperty()),
                                                          searchable.getSearchValue());
            case GREATER_THAN_EQUALS:
                return builder.greaterThanOrEqualTo(root.get(searchable.getSearchProperty()),
                                                             searchable.getSearchValue());
            case IN:
                return root
                        .get(searchable.getSearchProperty())
                        .in((Collection<?>) searchable.getSearchValue());
            case LIKE:
                return builder.like(root.get(searchable.getSearchProperty()),
                        "%" + searchable.getSearchValue().toString() + "%");
            case ILIKE:
                return builder.like(root.get(searchable.getSearchProperty()),
                        "%" + searchable.getSearchValue().toString().toLowerCase() + "%");
            default:
                return null;
        }
    }
}