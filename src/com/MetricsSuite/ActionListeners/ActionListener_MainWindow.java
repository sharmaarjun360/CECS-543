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
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ActionListener_MainWindow implements ActionListener, MouseListener {

    private static ActionListener_MainWindow actionListener_MainWindow_Instance = null;
    private Component context;
    private static JFrame activeSubWindow = null;
    private JFileChooser projectCodeFileChooser;

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
                break;
            case MetricsConstants.P_MENU_ITEM_PROJECT_CODE_ADD_CODE:
                File[] selectedFiles  = openCodeFiles(context);
                if(selectedFiles!=null) {
                    // TODO: show success message
                    ((MainWindow)context).toggleProjectCode(true);
                }
                break;
            case MetricsConstants.P_MENU_ITEM_METRICS_PROJECT_CODE_STATISTICS:

                if(this.projectCodeFileChooser != null){
                    File[] files = this.projectCodeFileChooser.getSelectedFiles();

                    if(files == null){
                        // TODO: show failure message
                    }

                    for (File selectedFile :
                            files) {
                        MetricsSuite.getInstance().getProjectData().getCodeFilesArray().add(selectedFile);
                        addCodeWindowPane(false, selectedFile);
                    }
                    ((MainWindow)context).updateTree(MetricsSuite.getInstance().getProjectData());

                    this.projectCodeFileChooser = null;
                    ((MainWindow)context).toggleProjectCode(false);
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
                ((MainWindow)context).metricsSuite.setProjectData(p1);
                ((MainWindow)context).createNewProject();
                ((JFrame)context).revalidate();
                createUIFromProjectData(p1,context);
            }
        }
        return true;
    }
    private File[] openCodeFiles(Component context) {

        FileNameExtensionFilter filter = new FileNameExtensionFilter(MetricsConstants.PROJECT_FILE_TYPES, MetricsConstants.PROJECT_FILE_TYPE,"java");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.setMultiSelectionEnabled(true);
        File[] selectedFiles = null;
        int fileChooserOption = fileChooser.showOpenDialog(context);
        ProjectData p1 = null;
        if (fileChooserOption == JFileChooser.APPROVE_OPTION) {
            selectedFiles = fileChooser.getSelectedFiles();
            //Add code call method to add Open code TAB here todo
        }
        this.projectCodeFileChooser = fileChooser;
        return selectedFiles;
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
        mainWindow.updateTree(MetricsSuite.getInstance().getProjectData());
    }

    private void addCodeWindowPane(boolean isSavedProject, File file){

            String filename = file.getName();
            MainWindow mainWindow = (MainWindow) context;
            CodeWindow codeWindow = new CodeWindow(file);
            JComponent panel = codeWindow.createNewDataPanel();
            mainWindow.mainTabbedPane.addTab(filename, null, panel, filename);
            mainWindow.mainTabbedPane.setSelectedIndex(mainWindow.mainTabbedPane.getTabCount() - 1);
            mainWindow.revalidate();
            mainWindow.updateTree(MetricsSuite.getInstance().getProjectData());
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.isPopupTrigger()) {
            //This Class is also adds right click popup when object is created.
            final TreePopup treePopup = new TreePopup(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
            if(e.isPopupTrigger()) {
                //This Class is also adds right click popup when object is created.
                final TreePopup treePopup = new TreePopup(e);
            }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /***
     * This Class is also adds right click popup when object is created.
     */
    class TreePopup extends JPopupMenu {
        private int x ;
        private int y ;
        public int counttab;
        private JTree tree;
        private TreePath path;
        private void initialize(MouseEvent e){
            x = e.getX();
            y = e.getY();
            tree = (JTree) e.getSource();
            path = tree.getPathForLocation(x, y);
        }
        private void setUpRightClickMenuOptions(){
            final String[] selectedNode = new String[1];
            JMenuItem open = new JMenuItem("Open");
            JMenuItem close = new JMenuItem("Close");
            JMenuItem delete = new JMenuItem("Delete");
            JTabbedPane tabbedPane = ((MainWindow)context).mainTabbedPane;
            int count = tabbedPane.getTabCount();
            open.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    System.out.println("Open "+path);
                    selectedNode[0] = path.getLastPathComponent()+"";
                    int i;
                    for (i=0; i<count; i++) {
                        if(selectedNode[0].equalsIgnoreCase(tabbedPane.getTitleAt(i))){
                            tabbedPane.setSelectedIndex(i);
                            break;
                        }
                    }
                    if(i==count){
                        ArrayList<FunctionPointData> fpArray = (ArrayList<FunctionPointData>) MetricsSuite.getInstance().getProjectData().getFpArray();
                        for(int j = 0 ; j< fpArray.size(); j++) {

                            if(fpArray.get(j).getTabName().equalsIgnoreCase(selectedNode[0])){
                                FunctionPointData fpToRefresh = fpArray.get(j);
                                MetricsSuite.getInstance().getProjectData().getFpArray().remove(fpArray.remove(j));
                                newFunctionPointPaneFromData((MainWindow) context,((MainWindow) context).mainTabbedPane,fpToRefresh);
                                MetricsSuite.getInstance().getProjectData().getFpArray().add(fpToRefresh);
                                fpToRefresh = null;
                                ((MainWindow) context).mainTabbedPane.setSelectedIndex(count);
                                context.revalidate();
                                return;
                            }
                        }
                        if(MetricsSuite.getInstance().getProjectData().getSmiData()!=null && selectedNode[0].equalsIgnoreCase("SMI")) {
                            addSMIPane(true, MetricsSuite.getInstance().getProjectData().getSmiData());
                            ((MainWindow)context).revalidate();
                            return;
                        }else{
                            //code Files open tab todo
                            List<File> codeFiles = MetricsSuite.getInstance().getProjectData().getCodeFilesArray();

                            for (int j = 0; j < codeFiles.size(); j++){
                                File selectedFile = codeFiles.get(j);
                                if(selectedFile.getName().equalsIgnoreCase(selectedNode[0])){
                                    addCodeWindowPane(false, selectedFile);
                                }
                            }
                        }
                    }
                    tabbedPane.revalidate();
                }
            });
            close.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    System.out.println("Close "+path);
                    selectedNode[0] = path.getLastPathComponent()+"";
                    for (int i=0; i<count; i++) {
                        if(selectedNode[0].equalsIgnoreCase(tabbedPane.getTitleAt(i))){
                            tabbedPane.removeTabAt(i);
                            tabbedPane.revalidate();
                            break;
                        }
                    }
                }
            });
            delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    System.out.println("Delete "+path);
                    selectedNode[0] = path.getLastPathComponent()+"";
                    int resultSelection = MetricsAlert.getInstance().showConfirmAlert(context,"Are you sure, you wan't to delete?");
        if (resultSelection == JOptionPane.YES_OPTION) {

                    for (int i=0; i<count; i++) {
                        if(selectedNode[0].equalsIgnoreCase(tabbedPane.getTitleAt(i))){
                            tabbedPane.removeTabAt(i);
                            ArrayList<FunctionPointData> fpArray = (ArrayList<FunctionPointData>) MetricsSuite.getInstance().getProjectData().getFpArray();
                            for(int j = 0 ; j< fpArray.size(); j++) {

                                if(fpArray.get(i).getTabName().equalsIgnoreCase(selectedNode[0])){
                                    MetricsSuite.getInstance().getProjectData().getFpArray().remove(fpArray.remove(i));

                                    ((MainWindow)context).updateTree(MetricsSuite.getInstance().getProjectData());
                                    ((MainWindow)context).revalidate();
                                return;
                                }
                            }
                            if(MetricsSuite.getInstance().getProjectData().getSmiData()!=null) {
                                MetricsSuite.getInstance().getProjectData().setSmiData(null);
                                ((MainWindow)context).updateTree(MetricsSuite.getInstance().getProjectData());
                                ((MainWindow)context).enableSMIMenu(true);
                                ((MainWindow)context).revalidate();
                                return;
                            }else{

                            }
                            //else if(MetricsSuite.getInstance().getProjectData().getNewTabs().contains(selectedNode[0])){
//
//                            }
                            break;
                        }
                    }
                }
                }
            });
            add(open);
            add(new JSeparator());
            add(close);
            add(new JSeparator());
            add(delete);
        }
        private void setUpRightClick(){
            if (path == null)
                return;

            DefaultMutableTreeNode rightClickedNode = (DefaultMutableTreeNode) path
                    .getLastPathComponent();

            TreePath[] selectionPaths = tree.getSelectionPaths();

            boolean isSelected = false;
            if (selectionPaths != null) {
                for (TreePath selectionPath : selectionPaths) {
                    if (selectionPath.equals(path)) {
                        isSelected = true;
                    }
                }
            }
            if (!isSelected) {
                tree.setSelectionPath(path);
            }

            if (rightClickedNode.isLeaf()) {
                show(tree, x, y);
            }
        }
        public TreePopup(MouseEvent e) {
            initialize(e);
            setUpRightClickMenuOptions();
            setUpRightClick();
        }
    }
}
