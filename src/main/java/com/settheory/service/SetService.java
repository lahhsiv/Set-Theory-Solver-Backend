package com.settheory.service;

import com.settheory.model.OperationResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class SetService {

    public OperationResponse union(Set<Integer> A, Set<Integer> B) {
        List<String> steps = new ArrayList<>();
        Set<Integer> result = new LinkedHashSet<>();

        steps.add("Take all elements from set A: " + formatSet(A));
        for (Integer element : A) {
            result.add(element);
        }

        steps.add("Add elements from set B that are not already in result");
        for (Integer element : B) {
            if (!result.contains(element)) {
                steps.add("Adding element " + element + " from B");
                result.add(element);
            } else {
                steps.add("Element " + element + " from B is already present, skipping");
            }
        }

        steps.add("Final result = " + formatSet(result));
        return new OperationResponse(formatSet(result), steps);
    }

    public OperationResponse intersection(Set<Integer> A, Set<Integer> B) {
        List<String> steps = new ArrayList<>();
        Set<Integer> result = new LinkedHashSet<>();

        steps.add("Compare elements of A and B to find common elements");
        for (Integer element : A) {
            steps.add("Checking element " + element + " from A");
            if (B.contains(element)) {
                steps.add("Element " + element + " is present in B, adding to intersection");
                result.add(element);
            } else {
                steps.add("Element " + element + " is NOT present in B");
            }
        }

        steps.add("Final result = " + formatSet(result));
        return new OperationResponse(formatSet(result), steps);
    }

    public OperationResponse complement(Set<Integer> A, Set<Integer> U) {
        List<String> steps = new ArrayList<>();
        Set<Integer> result = new LinkedHashSet<>();

        steps.add("Look at all elements in Universal Set U: " + formatSet(U));
        for (Integer element : U) {
            if (!A.contains(element)) {
                steps.add("Element " + element + " is in U but not in A, adding to complement");
                result.add(element);
            } else {
                steps.add("Element " + element + " is in A, skipping");
            }
        }

        steps.add("Final result = " + formatSet(result));
        return new OperationResponse(formatSet(result), steps);
    }

    public OperationResponse cartesianProduct(Set<Integer> A, Set<Integer> B) {
        List<String> steps = new ArrayList<>();
        List<String> resultElements = new ArrayList<>(); // Using List of Strings to represent tuples like "(1,2)"

        steps.add("For each element in A, pair it with every element in B");
        for (Integer a : A) {
            steps.add("Taking element " + a + " from A");
            for (Integer b : B) {
                String pair = "(" + a + "," + b + ")";
                steps.add("  Pairing " + a + " with " + b + " from B: " + pair);
                resultElements.add(pair);
            }
        }

        String finalResultStr = "{" + String.join(",", resultElements) + "}";
        steps.add("Final result = " + finalResultStr);
        return new OperationResponse(finalResultStr, steps);
    }

    public OperationResponse powerSet(Set<Integer> A) {
        List<String> steps = new ArrayList<>();
        List<Set<Integer>> subsets = new ArrayList<>();
        // Start with empty set
        subsets.add(new LinkedHashSet<>());
        steps.add("Start with the empty set {}");

        for (Integer element : A) {
            steps.add("Processing element: " + element);
            int currentSize = subsets.size();
            for (int i = 0; i < currentSize; i++) {
                Set<Integer> newSubset = new LinkedHashSet<>(subsets.get(i));
                newSubset.add(element);
                subsets.add(newSubset);
                steps.add("  Generated new subset by adding " + element + " to " + formatSet(subsets.get(i)) + " -> " + formatSet(newSubset));
            }
        }

        List<String> subsetStrings = new ArrayList<>();
        for (Set<Integer> subset : subsets) {
            subsetStrings.add(formatSet(subset));
        }

        String finalResultStr = "{" + String.join(",", subsetStrings) + "}";
        steps.add("Final power set contains " + subsets.size() + " subsets");
        steps.add("Final result = " + finalResultStr);
        return new OperationResponse(finalResultStr, steps);
    }

    public OperationResponse subsetCheck(Set<Integer> A, Set<Integer> B) {
        List<String> steps = new ArrayList<>();
        boolean isSubset = true;

        steps.add("Checking if all elements of A " + formatSet(A) + " exist in B " + formatSet(B));
        for (Integer element : A) {
            if (B.contains(element)) {
                steps.add("Element " + element + " from A is found in B");
            } else {
                steps.add("Element " + element + " from A is NOT found in B");
                isSubset = false;
                break;
            }
        }

        String resultStr = isSubset ? "True (A ⊆ B)" : "False (A ⊄ B)";
        if (isSubset) {
            steps.add("All elements of A are in B, so A is a subset of B");
        } else {
            steps.add("Not all elements of A are in B, so A is not a subset of B");
        }

        steps.add("Final result: " + resultStr);
        return new OperationResponse(resultStr, steps);
    }

    private String formatSet(Set<Integer> set) {
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
