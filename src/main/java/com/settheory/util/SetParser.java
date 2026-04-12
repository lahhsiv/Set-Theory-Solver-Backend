package com.settheory.util;

import java.util.LinkedHashSet;
import java.util.Set;

public class SetParser {

    public static Set<Integer> parseSet(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new LinkedHashSet<>();
        }

        input = input.trim();
        
        // Validation: must start with { and end with }
        if (!input.startsWith("{") || !input.endsWith("}")) {
            throw new IllegalArgumentException("Invalid set format. Sets must be enclosed in '{}'. Example: {1,2,3}");
        }

        String content = input.substring(1, input.length() - 1).trim();
        Set<Integer> result = new LinkedHashSet<>(); // Keeps insertion order, typical for nice output

        if (content.isEmpty()) {
            return result; // Empty set
        }

        String[] elements = content.split(",");
        for (String element : elements) {
            try {
                result.add(Integer.parseInt(element.trim()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid element inside set: '" + element.trim() + "'. Expected an integer.");
            }
        }

        return result;
    }
    
    public static String formatSet(Set<Integer> set) {
        if (set == null || set.isEmpty()) {
            return "{}";
        }
        
        StringBuilder sb = new StringBuilder("{");
        int count = 0;
        for (Integer elem : set) {
            sb.append(elem);
            count++;
            if (count < set.size()) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
