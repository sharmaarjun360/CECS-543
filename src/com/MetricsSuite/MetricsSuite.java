package com.MetricsSuite;

import com.MetricsSuite.Models.ProjectData;
import com.MetricsSuite.Windows.MainWindow;

public class MetricsSuite {

    private static MetricsSuite metricsSuiteInstance = null;

    private ProjectData projectData;

    String selectedLanguage;
    int languageCodeSize;

    public ProjectData getProjectData() {
        return projectData;
    }
    public void setProjectData(ProjectData projectData) {
        this.projectData = projectData;
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

    private MetricsSuite() {
        this.selectedLanguage = "Java";
        this.languageCodeSize = 55;
    }
    public static MetricsSuite getInstance(){
        if(metricsSuiteInstance == null){
            metricsSuiteInstance = new MetricsSuite();
        }
        return metricsSuiteInstance;
    }
    public static void main(String[] args){
        MainWindow mainWindow = new MainWindow(MetricsSuite.getInstance());
        mainWindow.setVisible(true);
    }
}
