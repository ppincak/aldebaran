package com.aldebaran.rest.search;

import java.util.ArrayList;
import java.util.List;


public abstract class ListTypeConverter<T> implements TypeConverter<String, List<T>> {

    protected List<T> convert(String s, StringConverter<T> converter) {
        List<T> resultList = new ArrayList<>();
        if(s == null || s.isEmpty()) {
            return resultList;
        }

        String[] elements =
                s.split(SearchRequest.LIST_SPLIT_CHAR);

        for(String element: elements) {
            try {
                resultList.add(converter.convert(element));
            } catch (Exception ignored) {}
        }
        return resultList;
    }

    public interface StringConverter<TTarget> {
        TTarget convert(String source);
    }

    public static class LongListTypeConverter extends ListTypeConverter<Long> {

        @Override
        public List<Long> convert(String s) {
            return this.convert(s, Long::valueOf);
        }
    }

    public static class StringListTypeConverter extends ListTypeConverter<String> {

        @Override
        public List<String> convert(String s) {
            return this.convert(s, source -> source);
        }
    }
}
