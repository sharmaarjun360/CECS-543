package com.MetricsSuite;

public class MetricsSuite {

    ProjectData projectData;

    public ProjectData getProjectData() {
        return projectData;
    }

    public void setProjectData(ProjectData projectData) {
        this.projectData = projectData;
    }

    public static void main(String[] args){
        MainWindow mainWindow = new MainWindow(new MetricsSuite());
        mainWindow.setVisible(true);
    }
}
