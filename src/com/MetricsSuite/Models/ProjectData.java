package com.MetricsSuite.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProjectData implements Serializable {

    private String projectName;
    private String productName;
    private String creator;
    private String comments;

    private List<FunctionPointData> fpArray;

    private ArrayList<String> codeFilesArray;

    private SMIData smiData;

    public ProjectData(){
        fpArray = new ArrayList<>();
        codeFilesArray = new ArrayList<>();
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

    public SMIData getSmiData() {
        return smiData;
    }

    public void setSmiData(SMIData smiData) {
        this.smiData = smiData;
    }
    public ArrayList<String> getCodeFilesArray() {
        return codeFilesArray;
    }

    public void setCodeFilesArray(ArrayList<String> codeFilesArray) {
        this.codeFilesArray = codeFilesArray;
    }
}
