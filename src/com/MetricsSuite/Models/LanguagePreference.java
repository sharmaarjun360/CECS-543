package com.MetricsSuite.Models;

public class LanguagePreference {

    public String name;
    int averageCodeSize;
    int order;

    public LanguagePreference(String name, int averageCodeSize, int order){
        this.name = name;
        this.averageCodeSize = averageCodeSize;
        this.order = order;
    }
}
