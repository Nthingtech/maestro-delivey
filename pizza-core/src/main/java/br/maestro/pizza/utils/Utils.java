package br.maestro.pizza.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Utils {

    public static String normalizedName (String name) {
        return name.trim()
                .replaceAll(" +", " ")
                .toLowerCase();
    }

    public static String toTitleCase(String text) {
        if (text == null || text.isBlank()) {
            return text;
        }

        return Arrays.stream(text.split("\\s+"))
                .filter(word -> !word.isEmpty())
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
