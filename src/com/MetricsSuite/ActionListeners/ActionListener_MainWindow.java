package com.MetricsSuite.ActionListeners;

import com.MetricsSuite.Alert.MetricsAlert;
import com.MetricsSuite.Error.MetricsError;
import com.MetricsSuite.FileChooser.SingleRootFileSystemView;
import com.MetricsSuite.GlobalConstants.MetricsConstants;
import com.MetricsSuite.MetricsSuite;
import com.MetricsSuite.Models.FunctionPointData;
import com.MetricsSuite.Models.ProjectData;
import com.MetricsSuite.Models.SMIData;
import com.MetricsSuite.Windows.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

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
                    boolean isSaved = saveProject(MetricsSuite.getInstance().getProjectData());
                    if(isSaved){
                        MetricsAlert.getInstance().showAlert(context,"Project Saved Successfully.");
                    }
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

                // open window to ask for function point tab name
                FunctionPointNameWindow fpNameWindow = new FunctionPointNameWindow((MainWindow) context);
                fpNameWindow.setVisible(true);
                break;
            case MetricsConstants.P_MENU_ITEM_METRICS_ENTER_SMI_DATA:
                ProjectData projectData1 = ((MainWindow) context).metricsSuite.getProjectData();
                if(projectData1 == null){ MetricsAlert.getInstance().showAlert(
                        (context), MetricsConstants.P_ALERT_CREATE_PROJECT);
                    return;
                }else if (projectData1 !=null){
                        addSMIPane(false, null);
                }
            default:
        }
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
//            MetricsAlert.getInstance().showAlert(context,"Project is null.");
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
            if(p1!=null){
                ((MainWindow)context).createNewProject();
                ((JFrame)context).revalidate();
                ((MainWindow)context).metricsSuite.setProjectData(p1);
                createUIFromProjectData(p1,context);
            }
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

    private void createUIFromProjectData(ProjectData projectData, Component context) {
        // TODO: 07/03/20 UI creation on new thread other than main thread
        if(!projectData.getFpArray().isEmpty()){
            ArrayList<FunctionPointData> tempFunctionPointDataArrayList = (ArrayList<FunctionPointData>) projectData.getFpArray();
            Object [] some_Array = tempFunctionPointDataArrayList.toArray();
            for(int i=0;i<some_Array.length;i++){
                newFunctionPointPaneFromData((JFrame) context, ((MainWindow) context).mainTabbedPane, (FunctionPointData) some_Array[i]);
            }
        }

        if(projectData.getSmiData() != null){
            addSMIPane(true, projectData.getSmiData());
            ((MainWindow)this.context).enableSMIMenu(false);
        }
    }

    private void newFunctionPointPaneFromData(JFrame parentFrame, JTabbedPane mainTabbedPane, FunctionPointData functionPointData) {
        FunctionPointWindow fp = new FunctionPointWindow((MainWindow) context,true, functionPointData, functionPointData.getTabName());
        JComponent panel = fp.createNewFunctionPointPanel();
        fp.updateFunctionPointWindow();
        mainTabbedPane.addTab(functionPointData.getTabName(), null, panel, functionPointData.getTabName());
        parentFrame.revalidate();
    }

    private void addSMIPane(boolean isSavedProject, SMIData smiData){
        MainWindow mainWindow = (MainWindow) context;
        SMIWindow smiWindow = new SMIWindow(mainWindow, isSavedProject, smiData);

        JComponent panel = smiWindow.createNewSMIPanel();
        if(isSavedProject){
            smiWindow.updateSMITable();
        }

        mainWindow.mainTabbedPane.addTab(MetricsConstants.P_SMI_TAB_TITLE, null, panel, "SMI");
        mainWindow.mainTabbedPane.setSelectedIndex(mainWindow.mainTabbedPane.getTabCount() - 1);

        mainWindow.revalidate();
        mainWindow.enableSMIMenu(false);
    }

    private JFrame openLanguageWindow() {
        if (activeSubWindow != null) {
            activeSubWindow.dispose();
            activeSubWindow = null;
        }
        LanguageWindow languageWindow = new LanguageWindow((MainWindow) this.context);
        languageWindow.setVisible(true);
        activeSubWindow = languageWindow;
        return languageWindow;
    }

    private void exitTheApplication() {
        System.exit(0);
    }
}
