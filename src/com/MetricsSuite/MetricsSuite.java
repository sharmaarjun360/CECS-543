package com.MetricsSuite;

import com.MetricsSuite.Models.ProjectData;
import com.MetricsSuite.Windows.MainWindow;

public class MetricsSuite {

    private static MetricsSuite metricsSuiteInstance = null;

    private ProjectData projectData;
    public ProjectData getProjectData() {
        return projectData;
    }
    public void setProjectData(ProjectData projectData) {
        this.projectData = projectData;
    }

    private MetricsSuite() {

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
