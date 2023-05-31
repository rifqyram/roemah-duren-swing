package com.xyz.roemahduren.util;

public class Utility {

    public static String toTitleCase(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder converted = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : text.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                c = Character.toTitleCase(c);
                capitalizeNext = false;
            } else {
                c = Character.toLowerCase(c);
            }

            converted.append(c);
        }

        return converted.toString();
    }

}
