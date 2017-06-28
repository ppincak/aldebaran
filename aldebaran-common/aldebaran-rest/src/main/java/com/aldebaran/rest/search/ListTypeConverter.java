package com.aldebaran.rest.search;

import java.util.ArrayList;
import java.util.List;


public abstract class ListTypeConverter<T> implements TypeConverter<String, List<T>> {

    public static class LongListTypeConverter extends ListTypeConverter<Long> {

        @Override
        public List<Long> convert(String s) {
            List<Long> resultList = new ArrayList<>();
            if(s == null || s.isEmpty()) {
                return resultList;
            }

            String[] elements =
                    s.split(SearchRequest.LIST_SPLIT_CHAR);

            for(String element: elements) {
                try {
                    resultList.add(Long.valueOf(element));
                } catch (NumberFormatException ignored) {}
            }
            return resultList;
        }
    }
}
