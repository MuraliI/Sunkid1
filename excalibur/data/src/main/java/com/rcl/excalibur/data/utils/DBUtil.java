package com.rcl.excalibur.data.utils;

public final class DBUtil {

    private static final String COL_ID = "Id";

    private DBUtil() {
    }

    private static String condition(String operator, String... conditions) {
        if (conditions == null || conditions.length == 0) {
            throw new IllegalArgumentException("Conditions must be non-null and non-empty.");
        }

        final StringBuilder builder = new StringBuilder();
        builder.append(checkValue(conditions[0]));
        for (int index = 1; index < conditions.length; index++) {
            builder.append(String.format(" %s ", operator));
            builder.append(checkValue(conditions[index]));
        }
        return builder.toString();
    }

    private static String checkValue(final String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value must be non-null and non-empty.");
        }
        return value;
    }

    public static String and(String... condition) {
        return condition("AND", condition);
    }

    public static String or(String... condition) {
        return condition("OR", condition);
    }


    public static String eqId(long value) {
        return String.format("%s = %s", COL_ID, value);
    }

    public static String eq(String column, int value) {
        return String.format("%s = %s", column, value);
    }

    public static String eq(String column, float value) {
        return String.format("%s = %s", column, value);
    }

    public static String eq(String column, String value) {
        return String.format("%s = '%s'", column, value);
    }

    public static String eq(String column) {
        return String.format("%s = ?", column);
    }

    public static <T> String notEq(String column, T value) {
        return String.format("%s != %s", column, value);
    }

    public static String isTrue(String column) {
        return String.format("%s = '1'", column);
    }

    public static String isFalse(String column) {
        return String.format("%s = '0'", column);
    }

    public static String asc(String column) {
        return String.format("%s ASC", column);
    }

    public static String desc(String column) {
        return String.format("%s DESC", column);
    }

    public static String eq(String column, boolean value) {
        return String.format("%s = '%s'", column, value);

    }
}
