package com.aldebaran.omanager.core.repositories;

import com.aldebaran.rest.search.SearchCriterion;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


public class SearchCriteriaSpecification<T, Y extends Comparable<Y>> implements Specification<T> {

    private final SearchCriterion<Y> criterion;

    public SearchCriteriaSpecification(SearchCriterion<Y> criterion) {
        this.criterion = criterion;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criterion.getOperator()) {
            case EQUALS:
                return builder.equal(root.get(criterion.getPropertyName()),
                                              criterion.getValue());
            case NOT_EQUALS:
                return builder.notEqual(root.get(criterion.getPropertyName()),
                                                 criterion.getValue());
            case LESS_THAN:
                return builder.lessThan(root.get(criterion.getPropertyName()),
                                                 criterion.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(criterion.getPropertyName()),
                                                    criterion.getValue());
            case LESS_THAN_EQUALS:
                return builder.lessThanOrEqualTo(root.get(criterion.getPropertyName()),
                                                          criterion.getValue());
            case GREATER_THAN_EQUALS:
                return builder.greaterThanOrEqualTo(root.get(criterion.getPropertyName()),
                                                             criterion.getValue());
            case IN:
                return root
                        .get(criterion.getPropertyName())
                        .in((Collection<?>) criterion.getValue());
            case LIKE:
                return builder.like(root.get(criterion.getPropertyName()),
                                            "%" + criterion.getValue().toString() + "%");
            case ILIKE:
                return builder.like(root.get(criterion.getPropertyName()),
                                            "%" + criterion.getValue().toString().toLowerCase() + "%");
            default:
                return null;
        }
    }

    private static <T, Y extends Comparable<Y>>Specification<T> build(SearchCriterion<Y> criterion) {
        return new SearchCriteriaSpecification<>(criterion);
    }

    @SuppressWarnings("unchecked")
    public static <T, Y extends Comparable<Y>>Specification<T> buildWithAnd(Set<SearchCriterion> criteria) {
        if(criteria.isEmpty()) {
            throw new RuntimeException();
        }
        Iterator<SearchCriterion> iterator = criteria.iterator();
        SearchCriterion searchCriterion = iterator.next();
        Specifications<T> specifications = Specifications.where(build(searchCriterion));
        while (iterator.hasNext()) {
            SearchCriterion criterion = iterator.next();
            specifications = specifications.and(build(criterion));
        }
        return specifications;
    }
}