package com.aldebaran.rest.search;

import com.aldebaran.rest.error.GeneralErrorEvents;
import com.aldebaran.rest.error.event.ApplicationException;

import javax.ws.rs.QueryParam;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchRequest {

    private static final String SPLIT_CHAR = ";";
    private static final Pattern SEARCH_PATTERN =
            Pattern.compile("([a-zA-Z]+)(:eq:|:neq:|:it:|:gt:|:lte:|:gte:|:in:|:li:|:ili:)([a-zA-Z0-9]+)");

    @QueryParam("search")
    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Set<SearchCriterion> toSearchCriteria(Map<String, SearchDescriptor> descriptorsMap) {
        Set<SearchCriterion> criteria = new HashSet<>();
        if(search == null || search.isEmpty()) {
            return criteria;
        }

        String[] searchQueries = search.split(SPLIT_CHAR);
        for(String searchQuery: searchQueries) {
            Matcher matcher = SEARCH_PATTERN.matcher(searchQuery);
            if(matcher.matches() == false) {
                continue;
            }
            String propertyName = matcher.group(1);
            SearchDescriptor searchDescriptor = descriptorsMap.get(propertyName);
            if(searchDescriptor == null) {
                continue;
            }
            SearchOperator searchOperator = SearchOperator.getByLabel(matcher.group(2));
            if(searchOperator == null) {
                continue;
            }
            if(searchDescriptor.getSupportedOperators().contains(searchOperator) == false) {
                throw new ApplicationException(GeneralErrorEvents.UNSUPPORTED_SEARCH_OPERATOR);
            }
            String value = matcher.group(3);

            SearchCriterion searchCriterion =
                    new SearchCriterion<>(searchOperator,
                                          searchDescriptor.getPropertyName(),
                                          value);
            criteria.add(searchCriterion);
        }
        return criteria;
    }
}