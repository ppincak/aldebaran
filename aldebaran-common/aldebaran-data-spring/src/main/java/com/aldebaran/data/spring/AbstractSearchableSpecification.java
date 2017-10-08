package com.aldebaran.data.spring;

import com.aldebaran.utils.Searchable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Collection;


public abstract class AbstractSearchableSpecification<T, Y extends Comparable<Y>> implements Specification<T> {

    private final Searchable<Y> searchable;

    protected AbstractSearchableSpecification(Searchable<Y> searchable) {
        this.searchable = searchable;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path<Y> searchProperty;

        if(searchable.getSearchProperty().contains(".")) {
            String[] splitProperty =
                    searchable.getSearchProperty().split("\\.");

            searchProperty =
                    assemblePath(root.get(splitProperty[0]), splitProperty, 1);

        } else {
            searchProperty = root.get(searchable.getSearchProperty());
        }

        switch (searchable.getOperator()) {
            case EQUALS:
                return builder
                        .equal(searchProperty,
                               searchable.getSearchValue());
            case NOT_EQUALS:
                return builder
                        .notEqual(searchProperty,
                                  searchable.getSearchValue());
            case LESS_THAN:
                return builder
                        .lessThan(searchProperty,
                                  searchable.getSearchValue());
            case GREATER_THAN:
                return builder
                        .greaterThan(searchProperty,
                                     searchable.getSearchValue());
            case LESS_THAN_EQUALS:
                return builder
                        .lessThanOrEqualTo(searchProperty,
                                           searchable.getSearchValue());
            case GREATER_THAN_EQUALS:
                return builder
                        .greaterThanOrEqualTo(searchProperty,
                                              searchable.getSearchValue());
            case IN:
                return searchProperty
                        .in((Collection<?>) searchable.getSearchValue());
            case LIKE:
                return builder
                        .like((Expression<String>) searchProperty,
                             "%" + searchable.getSearchValue().toString() + "%");
            case ILIKE:
                return builder
                        .like((Expression<String>) searchProperty,
                             "%" + searchable.getSearchValue().toString().toLowerCase() + "%");
            default:
                return null;
        }
    }

    private Path<Y> assemblePath(Path<Y> path, String[] splitProperty, int depth) {
        if(splitProperty.length == depth) {
            return path;
        }
        return assemblePath(path.get(splitProperty[depth]), splitProperty, depth + 1);
    }
}