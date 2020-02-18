package com.MetricsSuite;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class MainWindow extends JFrame implements ActionListener {

    JMenuBar menubar;
    JMenu file, edit, preferences, metrics, fp, help;
    JMenuItem new_menuItem, open, save, exit,language, fpData;

    public MetricsSuite metricsSuite;

    static String TITLE = "CECS 543 Metrics Suite";
    static int width = 600;
    static int height = 600;
    static JFrame activeSubWindow = null;

    public MainWindow(MetricsSuite parent){
        this.metricsSuite = parent;
        initComponent();
        setTitle(MainWindow.TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MainWindow.width,MainWindow.height);
        setLocation(200,100);
    }

    public MetricsSuite getMetricsSuite() {
        return metricsSuite;
    }

    public void setMetricsSuite(MetricsSuite metricsSuite) {
        this.metricsSuite = metricsSuite;
    }

    public void initComponent(){
        addMenuBar();
        addActionEvent();
    }

    private void addMenuBar(){
        JFrame context = this;
        menubar = new JMenuBar();
        setJMenuBar(menubar);

        file = new JMenu("File");
        menubar.add(file);

        new_menuItem = new JMenuItem("New");
        file.add(new_menuItem);

        open = new JMenuItem("Open");
        file.add(open);

        save = new JMenuItem("Save");
        file.add(save);

        exit = new JMenuItem("Exit");
        file.add(exit);

        edit = new JMenu("Edit");
        menubar.add(edit);

        preferences = new JMenu("Preferences");
        menubar.add(preferences);

        language = new JMenuItem("Language");
        preferences.add(language);

        metrics = new JMenu("Metrics");
        menubar.add(metrics);

        fp = new JMenu("Function Points");
        metrics.add(fp);

        fpData = new JMenuItem("Enter FP Data");
        fp.add(fpData);

        help = new JMenu("Help");
        menubar.add(help);
    }

    public void addActionEvent() {
        new_menuItem.addActionListener(this);
        exit.addActionListener(this);
        language.addActionListener(this);
        save.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(activeSubWindow != null){
            activeSubWindow.dispose();
            activeSubWindow = null;
        }

        if(e.getSource()== new_menuItem){

            NewProject newProject = new NewProject(this);
            newProject.setVisible(true);
            activeSubWindow = newProject;
        } else if(e.getSource() == exit){

            this.dispose();
        } else if(e.getSource()== language){

            Language languageWindow = new Language();
            languageWindow.setVisible(true);
            activeSubWindow = languageWindow;
        } else if(e.getSource() == fpData){
            JTabbedPane tab = new JTabbedPane();
        } else if(e.getSource() == save){

            try {
//                File projectFile = new File("./../../../projectData/" + this.metricsSuite.getProjectData().getProjectName() + ".ms");
                File projectFile = new File("./projectData/" + this.metricsSuite.getProjectData().getProjectName() + ".ms");
                FileOutputStream fos = new FileOutputStream(projectFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(this.metricsSuite.getProjectData());

                oos.close();
            } catch (Exception ex){
                System.out.println(ex);
            }
        }
    }
}
