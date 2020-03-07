package com.MetricsSuite.ActionListeners;

import com.MetricsSuite.*;
import com.MetricsSuite.Alert.MetricsAlert;
import com.MetricsSuite.FileChooser.SingleRootFileSystemView;
import com.MetricsSuite.Models.ProjectData;
import com.MetricsSuite.Windows.FunctionPointWindow;
import com.MetricsSuite.Windows.LanguageWindow;
import com.MetricsSuite.Windows.MainWindow;
import com.MetricsSuite.Windows.NewProjectWindow;
import com.MetricsSuite.Error.MetricsError;
import com.MetricsSuite.GlobalConstants.MetricsConstants;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ActionListener_MainWindow implements ActionListener {

    private static ActionListener_MainWindow actionListener_MainWindow_Instance = null;
    private Component context;
    private static JFrame activeSubWindow = null;

    private ActionListener_MainWindow(Component context) {
        this.context = context;
    }

    /**
     * Returns singleton instance
     *
     * @param context
     * @return
     */
    public static ActionListener_MainWindow getInstance(Component context) {
        if (actionListener_MainWindow_Instance == null)
            actionListener_MainWindow_Instance = new ActionListener_MainWindow(context);
        return actionListener_MainWindow_Instance;
    }

    /**
     * Action performed on main window are managed here
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case MetricsConstants.P_MENU_ITEM_FILE_NEW:
                activeSubWindow = newProjectWindow(context);
                break;
            case MetricsConstants.P_MENU_ITEM_FILE_OPEN:
                openProject(context);
                break;
            case MetricsConstants.P_MENU_ITEM_FILE_EXIT:
                exitTheApplication();
                break;
            case MetricsConstants.P_MENU_ITEM_FILE_SAVE:
                try {
                    saveProject(MetricsSuite.getInstance().getProjectData());
                } catch (IOException ioError) {
                    ioError.printStackTrace();
                } catch (MetricsError metricsError) {
                    MetricsAlert.getInstance().showAlert(context, metricsError.toString());
                }
                break;
            case MetricsConstants.P_MENU_ITEM_PREFERENCES_LANGUAGE:
                openLanguageWindow();
                break;
            case MetricsConstants.P_MENU_ITEM_METRICS_ENTER_FP_DATA:
                // TODO: 21/02/20 Use call backs to communicate between View and controller
                // getMainTabbedPane incorrect approch use callbacks instead
                newFunctionPointPane((JFrame) context, ((MainWindow) context).mainTabbedPane);
                break;
            default:
        }
    }

    /**
     * Saves Project data to location defined in Metrics Constant
     *
     * @param projectData
     * @return true after successful transaction
     * @throws MetricsError
     * @throws IOException
     */
    private boolean saveProject(ProjectData projectData) throws MetricsError, IOException {
        if (projectData == null) {
            throw new MetricsError(MetricsError.ERROR_CODE.ERROR_NULL_PROJECT);
        }
        String projectName = projectData.getProjectName();
        File projectDir = new File(MetricsConstants.PROJECT_DATA_PATH);
        if (!projectDir.exists()) {
            projectDir.mkdir();
        }
        File projectFile = new File(projectDir.getPath() + "/" + projectName + MetricsConstants.PROJECT_EXTENSION);
        FileOutputStream outFile;
        outFile = new FileOutputStream(projectFile);
        ObjectOutputStream outObject = new ObjectOutputStream(outFile);
        outObject.writeObject(projectData);
        outObject.close();
        return true;
    }

    /**
     * Opens JFile chooser, by default opens to the location defined in Metrics constant
     *
     * @param context
     * @return
     */
    private boolean openProject(Component context) {
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
            p1 = (ProjectData) readProjectDataFromFile(projectFilePath, MetricsSuite.getInstance().getProjectData());
        }
        return true;
    }

    /**
     * Reads the file at path selected
     *
     * @param filepath
     * @param objectType
     * @return
     */
    private Object readProjectDataFromFile(String filepath, Object objectType) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            objectType = objectIn.readObject();
            objectIn.close();
            return objectType;
        } catch (Exception ex) {
            MetricsAlert.getInstance().showAlert(context, new MetricsError(MetricsError.ERROR_CODE.ERROR_READING_PROJECT).toString());
//            ex.printStackTrace();
            return null;
        }
    }

    private JFrame openLanguageWindow() {
        if (activeSubWindow != null) {
            activeSubWindow.dispose();
            activeSubWindow = null;
        }
        LanguageWindow languageWindow = new LanguageWindow();
        languageWindow.setVisible(true);
        activeSubWindow = languageWindow;
        return languageWindow;
    }

    private JFrame newProjectWindow(Component context) {
        NewProjectWindow newProject = null;
        if (context instanceof MainWindow) {
            if (activeSubWindow != null) {
                activeSubWindow.dispose();
                activeSubWindow = null;
            }
            newProject = new NewProjectWindow((MainWindow) context);
            newProject.setVisible(true);
        }
        return newProject;
    }

    private void newFunctionPointPane(JFrame parentFrame, JTabbedPane mainTabbedPane) {

        addEmptyFunctionPointTabToMainPane(mainTabbedPane);
//        mainPane.setSize(context.getWidth(),context.getHeight());
        parentFrame.revalidate();
//        parentFrame.pack();
//        mainPane.setBounds(200,50,200,200);
    }

    private JComponent addEmptyFunctionPointTabToMainPane(JTabbedPane mainPane) {
        ImageIcon icon = createImageIcon(MetricsConstants.PROJECT_IMAGES + MetricsConstants.PROJECT_IMAGE_SUN);

        FunctionPointWindow fp = new FunctionPointWindow((MainWindow) context);
        JComponent panel = fp.createNewFunctionPointPanel();
//        JComponent panel = createNewFunctionPointPanel("We have to add custom panel here "+"@Method: addEmptyFunctionPointTabToMainPane \n @ class: ActionListener_MainWindow ");

        mainPane.addTab(MetricsConstants.P_TAB_TITLE, null, panel, "Some tool tip");
        return panel;
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MetricsSuite.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private void exitTheApplication() {
        System.exit(0);
    }
}
