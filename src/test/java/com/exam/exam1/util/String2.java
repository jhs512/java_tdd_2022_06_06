package com.exam.exam1.util;

public class String2 {
    private final StringBuilder builder;

    public String2(String str) {
        builder = new StringBuilder(str);
    }

    @Override
    public String toString() {
        return builder.toString();
    }

    public int findAndDeleteBeforeAll(String key) {
        int index = builder.indexOf(key);

        if (index == -1) {
            return index;
        }

        builder.delete(0, index + key.length());

        return index;
    }

    public int length() {
        return builder.length();
    }
}
