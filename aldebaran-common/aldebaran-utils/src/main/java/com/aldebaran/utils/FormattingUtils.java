package com.aldebaran.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FormattingUtils {

    public final static Pattern TIME_EXPRESSION_PATTERN =
            Pattern.compile("(-?[0-9]+)(w|d|h|m|s)\\b", Pattern.CASE_INSENSITIVE);

    public static long parseExpression(final String timeExpression) {
        if (timeExpression == null || timeExpression.length() == 0) {
            return 0L;
        }

        Long result = 0L;
        for (String timePart : timeExpression.split(" ")) {
            Matcher matcher = TIME_EXPRESSION_PATTERN.matcher(timePart);
            Long number;

            if (matcher.find()) {
                try {
                    number = Long.parseLong(matcher.group(1));
                } catch (NumberFormatException ex) {
                    throw new RuntimeException("Bad formatting of time expression");
                }
                String timeUnit = matcher.group(2);
                switch (timeUnit.toLowerCase()) {
                    case "w":
                        result += number * 7 * 24 * 60 * 60;
                        break;
                    case "d":
                        result += number * 24 * 60 * 60;
                        break;
                    case "h":
                        result += number * 60 * 60;
                        break;
                    case "m":
                        result += number * 60;
                        break;
                    case "s":
                        result += number;
                        break;
                    default:
                        throw new RuntimeException("Unsupported time-unit");
                }
            } else {
                throw new RuntimeException("Bad formatting of time expression");
            }
        }
        return result;
    }

    public static String stringify(Object object) {
        if(object == null) {
            return "''";
        }
        return object.toString();
    }
}