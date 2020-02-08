package com.MetricsSuite;

import javax.swing.*;
import java.awt.event.*;

public class MainWindow extends JFrame {

    static String TITLE = "CECS 543 Metrics Suite";
    static int width = 600;
    static int height = 600;

    public MainWindow(){
        initComponent();
        setTitle(MainWindow.TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MainWindow.width,MainWindow.height);
        setLocation(200,100);
    }

    public void initComponent(){
        addMenuBar();

    }

    private void addMenuBar(){
        JFrame context = this;
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu file = new JMenu("File");
        menubar.add(file);

        JMenuItem new_menuItem = new JMenuItem("New");
        file.add(new_menuItem);
        new_menuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                NewProject newProject = new NewProject(context);
                newProject.setVisible(true);
            }
        });

        JMenuItem open = new JMenuItem("Open");
        file.add(open);

        JMenuItem save = new JMenuItem("Save");
        file.add(save);

        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);

        JMenu edit = new JMenu("Edit");
        menubar.add(edit);

        JMenu preferences = new JMenu("Preferences");
        menubar.add(preferences);

        JMenuItem language = new JMenuItem("Language");
        preferences.add(language);

        JMenu metrics = new JMenu("Metrics");
        menubar.add(metrics);

        JMenu fp = new JMenu("Function Points");
        metrics.add(fp);

        JMenuItem fbData = new JMenuItem("Enter FP Data");
        fp.add(fbData);

        JMenu help = new JMenu("Help");
        menubar.add(help);
    }
}
