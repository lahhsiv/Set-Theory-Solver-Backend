package com.settheory.model;

import java.util.List;

public class OperationResponse {
    private String result;
    private List<String> steps;

    public OperationResponse() {}

    public OperationResponse(String result, List<String> steps) {
        this.result = result;
        this.steps = steps;
    }

    // Getters and Setters
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
