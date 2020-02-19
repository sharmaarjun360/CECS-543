package com.MetricsSuite.ActionListeners;

import com.MetricsSuite.Alert.MetricsAlert;
import com.MetricsSuite.LanguageWindow;
import com.MetricsSuite.NewProjectWindow;
import com.MetricsSuite.error.MetricsError;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionListener_MainWindow implements ActionListener {

    private static ActionListener_MainWindow actionListener_MainWindow_Instance = null;
    private  Component context;
    private static JFrame activeSubWindow = null;
    private ActionListener_MainWindow(Component context) {
        this.context = context;
    }

    public static ActionListener_MainWindow getInstance(Component context){
        if (actionListener_MainWindow_Instance == null)
            actionListener_MainWindow_Instance = new ActionListener_MainWindow(context);
        return actionListener_MainWindow_Instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(activeSubWindow != null){
            activeSubWindow.dispose();
            activeSubWindow = null;
        }

//        if(e.getSource() == new_menuItem){
//
//            NewProjectWindow newProject = new NewProjectWindow(this);
//            newProject.setVisible(true);
//            activeSubWindow = newProject;
//        } else if(e.getSource() == exit){
//
//            this.dispose();
//        } else if(e.getSource()== language){
//            LanguageWindow languageWindow = new LanguageWindow();
//            languageWindow.setVisible(true);
//            activeSubWindow = languageWindow;
//        } else if(e.getSource() == fpData){
//            JTabbedPane tab = new JTabbedPane();
//        } else if(e.getSource() == save){
//            try {
//                saveProject(this.metricsSuite.getProjectData());
//            } catch ( IOException ioError) {
//                ioError.printStackTrace();
//            }catch (MetricsError metricsError){
//                MetricsAlert.getInstance().showAlert(this,metricsError.toString());
//            }
//        }
//        else if(e.getSource() == open){
//            openProject(this);
//        }
    }
}
