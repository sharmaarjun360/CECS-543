package com.MetricsSuite;

import com.MetricsSuite.ActionListeners.ActionListener_MainWindow;
import com.MetricsSuite.Alert.MetricsAlert;
import com.MetricsSuite.FileChooser.SingleRootFileSystemView;
import com.MetricsSuite.error.MetricsError;
import com.MetricsSuite.globalConstants.MetricsConstants;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

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
        open.addActionListener(this);
//        save.addActionListener(ActionListener_MainWindow.getInstance(this));
//        open.addActionListener(ActionListener_MainWindow.getInstance(this));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(activeSubWindow != null){
            activeSubWindow.dispose();
            activeSubWindow = null;
        }

        if(e.getSource() == new_menuItem){

            NewProjectWindow newProject = new NewProjectWindow(this);
            newProject.setVisible(true);
            activeSubWindow = newProject;
        } else if(e.getSource() == exit){

            this.dispose();
        } else if(e.getSource()== language){
            LanguageWindow languageWindow = new LanguageWindow();
            languageWindow.setVisible(true);
            activeSubWindow = languageWindow;
        } else if(e.getSource() == fpData){
            JTabbedPane tab = new JTabbedPane();
        } else if(e.getSource() == save){
            try {
                saveProject(this.metricsSuite.getProjectData());
            } catch ( IOException ioError) {
                ioError.printStackTrace();
            }catch (MetricsError metricsError){
                MetricsAlert.getInstance().showAlert(this,metricsError.toString());
            }
        }
        else if(e.getSource() == open){
            openProject(this);
        }
    }

    public boolean saveProject( ProjectData projectData) throws MetricsError, IOException {
        if (projectData == null) {
            throw new MetricsError(MetricsError.ERROR_CODE.ERROR_NULL_PROJECT);
        }
        String projectName = projectData.projectName;
        File projectDir = new File(MetricsConstants.PROJECT_DATA_PATH);
        if (!projectDir.exists()) {
            projectDir.mkdir();
        }
        File projectFile = new File(projectDir.getPath()+"/" + projectName + MetricsConstants.PROJECT_EXTENSION);
        FileOutputStream outFile;
        outFile = new FileOutputStream(projectFile);
        ObjectOutputStream outObject = new ObjectOutputStream(outFile);
        outObject.writeObject(projectData);
        outObject.close();
        return true;
    }

    public boolean openProject(Component context) {
        File root = new File(MetricsConstants.PROJECT_DATA_PATH);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(MetricsConstants.PROJECT_NAME, MetricsConstants.PROJECT_FILE_TYPE);
        FileSystemView fsv = new SingleRootFileSystemView(root);
        JFileChooser fileChooser = new JFileChooser(fsv);
        fileChooser.setFileFilter(filter);
        String projectFilePath = "";
        int fileChooserOption = fileChooser.showOpenDialog(context);
        ProjectData p1 = null;
        if (fileChooserOption == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            projectFilePath = selectedFile.getAbsolutePath();
            System.out.println("Selected file: " + projectFilePath);
            p1 = (ProjectData) readProjectDataFromFile(projectFilePath,this.metricsSuite.projectData);
        }
        return true;
    }

    public Object readProjectDataFromFile(String filepath,Object objectType) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            objectType = objectIn.readObject();
            objectIn.close();
            return objectType;
        } catch (Exception ex) {
            MetricsAlert.getInstance().showAlert(this,new MetricsError(MetricsError.ERROR_CODE.ERROR_READING_PROJECT).toString());
//            ex.printStackTrace();
            return null;
        }
    }

}
