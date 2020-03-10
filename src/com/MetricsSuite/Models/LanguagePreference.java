package com.MetricsSuite.Models;

public class LanguagePreference {

    private String name;
    private int averageCodeSize;
    private int order;

    public LanguagePreference(String name, int averageCodeSize, int order){
        this.name = name;
        this.averageCodeSize = averageCodeSize;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAverageCodeSize() {
        return averageCodeSize;
    }

    public void setAverageCodeSize(int averageCodeSize) {
        this.averageCodeSize = averageCodeSize;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
