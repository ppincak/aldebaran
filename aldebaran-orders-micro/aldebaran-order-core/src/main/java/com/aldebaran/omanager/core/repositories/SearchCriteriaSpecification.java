package com.aldebaran.omanager.core.repositories;

import com.aldebaran.data.spring.AbstractSearchableSpecification;
import com.aldebaran.rest.search.SearchCriterion;
import com.aldebaran.utils.Searchable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.Iterator;
import java.util.Set;


public class SearchCriteriaSpecification<T, Y extends Comparable<Y>>
        extends AbstractSearchableSpecification<T, Y> implements Specification<T> {

    public SearchCriteriaSpecification(Searchable<Y> searchable) {
        super(searchable);
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