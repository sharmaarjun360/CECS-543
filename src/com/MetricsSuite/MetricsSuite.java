package com.MetricsSuite;

public class MetricsSuite {

    Project projectData;

    public Project getProjectData() {
        return projectData;
    }

    public void setProjectData(Project projectData) {
        this.projectData = projectData;
    }

    public static void main(String[] args){
        MainWindow mainWindow = new MainWindow(new MetricsSuite());
        mainWindow.setVisible(true);
    }
}
