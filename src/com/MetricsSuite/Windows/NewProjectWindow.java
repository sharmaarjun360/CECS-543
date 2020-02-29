package com.MetricsSuite.Windows;

import com.MetricsSuite.ActionListeners.NewProjectActionListener;
import com.MetricsSuite.Alert.MetricsAlert;
import com.MetricsSuite.MetricsSuite;
import com.MetricsSuite.Models.ProjectData;
import com.MetricsSuite.GlobalConstants.MetricsConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewProjectWindow extends JFrame {

    Container container = getContentPane();
    public MainWindow mainWindow;
    public JLabel mainLabel, projectName, productName, creator, comment;
    public JTextField projectName_text, productName_text, creator_text;
    public JTextArea comment_area;
    public JButton ok, cancel;

    public NewProjectWindow(MainWindow parentFrame){

        this.mainWindow = parentFrame;
        container.setLayout(null);
        initComponent();
        addActionEvent();
        setSize(MetricsConstants.NEW_PROJECT_WINDOW_WIDTH,MetricsConstants.NEW_PROJECT__WINDOW_HEIGHT);
        setLocation(MetricsConstants.NEW_PROJECT__WINDOW_LOCATION_X,MetricsConstants.NEW_PROJECT__WINDOW_LOCATION_Y);
        setTitle("New Project");

    }

    public void initComponent(){

        mainLabel = new JLabel("CECS 543 Metrics Suite New Project");
        mainLabel.setBounds(0,20,400,20);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(mainLabel);

        projectName = new JLabel("Project Name:");
        projectName.setBounds(20, 60,100,20);
        container.add(projectName);
        projectName_text = new JTextField();
        projectName_text.setBounds(120, 60,150,20);
        container.add(projectName_text);

        productName = new JLabel("Product Name:");
        productName.setBounds(20, 90,100,20);
        container.add(productName);
        productName_text = new JTextField();
        productName_text.setBounds(120, 90,150,20);
        container.add(productName_text);

        creator = new JLabel("Creator:");
        creator.setBounds(20, 120,100,20);
        container.add(creator);
        creator_text = new JTextField();
        creator_text.setBounds(120, 120,150,20);
        container.add(creator_text);

        comment = new JLabel("Comments:");
        comment.setBounds(20, 150,100,20);
        container.add(comment);
        comment_area = new JTextArea();
        comment_area.setBounds(20, 175,360,100);
        container.add(comment_area);

        // add button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBounds(10,290,200,50);
        container.add(buttonPanel);
        ok=new JButton("Ok");
        cancel=new JButton("Cancel");
        buttonPanel.add(ok);
        buttonPanel.add(cancel);

        container.add(buttonPanel);

    }

    public void addActionEvent() {
        NewProjectActionListener listener = new NewProjectActionListener(this);
        ok.addActionListener(listener);
        cancel.addActionListener(listener);
    }

    public void disposeWindow(){
        setVisible(false);
        dispose();
    }
}
