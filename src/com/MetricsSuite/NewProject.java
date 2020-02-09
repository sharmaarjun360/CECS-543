package com.MetricsSuite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewProject extends JFrame implements ActionListener {

    Container container = getContentPane();
    JFrame parentFrame;
    JLabel mainLabel, projectName, productName, creator, comment;
    JTextField projectName_text, productName_text, creator_text;
    JTextArea comment_area;
    JButton ok, cancel;

    public NewProject(JFrame parentFrame){

        this.parentFrame = parentFrame;
        container.setLayout(null);
        initComponent();
        addActionEvent();
        setSize(400,360);
        setLocation(300,200);
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



        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBounds(10,290,200,50);
        container.add(buttonPanel);
        ok=new JButton("Ok");
        cancel=new JButton("Cancel");
        buttonPanel.add(ok);
        buttonPanel.add(cancel);

        container.add(buttonPanel);
//        container.add(ok);
//        container.add(cancel);

    }

    public void addActionEvent() {
        ok.addActionListener(this);
        cancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()== ok){
            String projectName_str = projectName_text.getText();

            if(projectName_str == null || projectName_str.length()==0){
                // show prompt
                JOptionPane.showMessageDialog(this, "Please Enter Project Name");
                projectName_text.requestFocusInWindow();
            } else {
                //pass project name to main frame
                String title = MainWindow.TITLE + " - "+ projectName_str;
                this.parentFrame.setTitle(title);
                setVisible(false);
                dispose();
            }
        }

        if(e.getSource()==cancel){
            setVisible(false);
            dispose();
        }
    }
}
