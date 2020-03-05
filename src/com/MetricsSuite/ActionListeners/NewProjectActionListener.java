package com.MetricsSuite.ActionListeners;

import com.MetricsSuite.Alert.MetricsAlert;
import com.MetricsSuite.GlobalConstants.MetricsConstants;
import com.MetricsSuite.MetricsSuite;
import com.MetricsSuite.Models.ProjectData;
import com.MetricsSuite.Windows.NewProjectWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NewProjectActionListener implements ActionListener {

    private static NewProjectActionListener self = null;
    private NewProjectWindow newProjectWindow;

    public NewProjectActionListener(NewProjectWindow newProjectWindow){
        this.newProjectWindow = newProjectWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== this.newProjectWindow.ok){
            String projectName_str = this.newProjectWindow.projectName_text.getText();
            String productName_str = this.newProjectWindow.productName_text.getText();
            String creator_str = this.newProjectWindow.creator_text.getText();
            String comment_str = this.newProjectWindow.comment_area.getText();
            boolean closeWindow = false;
            ProjectData project = new ProjectData();

            if(projectName_str == null || projectName_str.length()==0){
                // show prompt
                MetricsAlert.getInstance().showAlert(this.newProjectWindow,"Please Enter Project Name");
                this.newProjectWindow.projectName_text.requestFocusInWindow();
                return;
            } else {
                // check for unique name
//                File tempFile = File.createTempFile(projectName_str,".ms");
                File projectDir = new File(MetricsConstants.PROJECT_DATA_PATH);
                File tempFile = new File(projectDir.getPath() + "/" + projectName_str + MetricsConstants.PROJECT_EXTENSION);

                if(tempFile.exists() == true){
                    MetricsAlert.getInstance().showAlert(this.newProjectWindow,"Project Name should be unique");
                    this.newProjectWindow.projectName_text.requestFocusInWindow();
                    return;
                }

                closeWindow = true;
                project.setProjectName(projectName_str);
            }

            if(productName_str != null && productName_str.length()!=0){
                project.setProductName(productName_str);
            }

            if(creator_str!= null && creator_str.length()!=0){
                project.setCreator(creator_str);
            }
            if(comment_str!= null && comment_str.length()!=0){
                project.setComments(comment_str);
            }

            if(closeWindow){
                //pass project name to main frame
                String title = MetricsConstants.PROJECT_TITLE + " - "+ projectName_str;
                this.newProjectWindow.mainWindow.setTitle(title);
                MetricsSuite.getInstance().setProjectData(project);
                this.newProjectWindow.disposeWindow();
                this.newProjectWindow.mainWindow.createNewProject();
            }
        }

        if(e.getSource()== this.newProjectWindow.cancel){
            this.newProjectWindow.disposeWindow();
        }
    }
}
