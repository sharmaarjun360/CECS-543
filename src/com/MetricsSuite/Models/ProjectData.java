package com.MetricsSuite.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProjectData implements Serializable {

    private String projectName;
    private String productName;
    private String creator;
    private String comments;

    String selectedLanguage;
    int languageCodeSize;

    private List<FunctionPointData> fpArray;

    public ProjectData(){
        fpArray = new ArrayList<>();
        this.selectedLanguage = "Java";
        this.languageCodeSize = 55;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<FunctionPointData> getFpArray() {
        return fpArray;
    }

    public void setFpArray(List<FunctionPointData> fpArray) {
        this.fpArray = fpArray;
    }

    public String getSelectedLanguage() {
        return selectedLanguage;
    }

    public void setSelectedLanguage(String selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }

    public int getLanguageCodeSize() {
        return languageCodeSize;
    }

    public void setLanguageCodeSize(int languageCodeSize) {
        this.languageCodeSize = languageCodeSize;
    }
}
