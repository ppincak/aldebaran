package com.aldebaran.rest.search;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public final class TypeOperators {

    private static final Map<Class<? extends Comparable<?>>, EnumSet<SearchOperator>> typeOperators = new HashMap<>();

    static {
        typeOperators.put(String.class,
                          EnumSet.of(SearchOperator.EQUALS,
                                     SearchOperator.NOT_EQUALS,
                                     SearchOperator.LIKE,
                                     SearchOperator.ILIKE));

        EnumSet<SearchOperator> numberOperations =
            EnumSet.of(SearchOperator.EQUALS,
                       SearchOperator.NOT_EQUALS,
                       SearchOperator.GREATER_THAN,
                       SearchOperator.GREATER_THAN_EQUALS,
                       SearchOperator.LESS_THAN,
                       SearchOperator.LESS_THAN_EQUALS);

        typeOperators.put(Integer.class, numberOperations);
        typeOperators.put(Long.class, numberOperations);
        typeOperators.put(BigDecimal.class, numberOperations);
    }

    public static EnumSet<SearchOperator> getOperators(Class<? extends Comparable<?>> clazz) {
        return typeOperators.get(clazz);
    }
}