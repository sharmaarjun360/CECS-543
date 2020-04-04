package com.MetricsSuite.Windows;

import com.MetricsSuite.ActionListeners.ActionListener_MainWindow;
import com.MetricsSuite.MetricsSuite;
import com.MetricsSuite.GlobalConstants.MetricsConstants;
import com.MetricsSuite.Models.FunctionPointData;
import com.MetricsSuite.Models.ProjectData;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.File;
import java.util.HashMap;


public class MainWindow extends JFrame {

    public MetricsSuite metricsSuite;
    public JTabbedPane mainTabbedPane = null;
    public JScrollPane mainScrollPane = null;
    private JMenu metrics,smi,project_code;
    private JMenuItem addProjectCode, projectCodeStatistics;
    public MainWindow(MetricsSuite parent){
        initComponent();
        setTitle(MetricsConstants.PROJECT_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MetricsConstants.MAIN_WINDOW_WIDTH,MetricsConstants.MAIN_WINDOW_HEIGHT);
        setLocation(MetricsConstants.MAIN_WINDOW_LOCATION_X,MetricsConstants.MAIN_WINDOW_LOCATION_Y);
        this.metricsSuite = parent;
    }

    /**
     *
     */
    public void initComponent(){
        addMenuBar(this);
        // TODO: 21/02/20 For Iteration 02 refer read me #1:
        mainScrollPane = addJTree(this, createJTree(MetricsConstants.P_JTREE_EMPTY),false);
        mainTabbedPane = addTabbedPane(this);
    }
    private void enableMetricsMenu(boolean enable){
        metrics.setEnabled(enable);
    }
    public void enableSMIMenu(boolean enable){
        smi.setEnabled(enable);
    }

    public void toggleProjectCode(boolean enable){
        projectCodeStatistics.setEnabled(enable);
        addProjectCode.setEnabled(!enable);
    }

    private JScrollPane addJTree(JFrame parentFrame, JTree jTree, boolean addMouseListener) {
        JScrollPane tempScrollPane = new JScrollPane(jTree);
        tempScrollPane.setPreferredSize(new Dimension(MetricsConstants.JTREE_WINDOW_WIDTH, MetricsConstants.JTREE_WINDOW_HEIGHT));
        tempScrollPane.setMaximumSize(new Dimension(MetricsConstants.JTREE_WINDOW_WIDTH, MetricsConstants.JTREE_WINDOW_HEIGHT));
        parentFrame.add(tempScrollPane, BorderLayout.WEST);
        if(addMouseListener){
        jTree.addMouseListener(ActionListener_MainWindow.getInstance(this));
        }
        return tempScrollPane;
    }

    private JTabbedPane addTabbedPane(JFrame parentFrame) {
        JTabbedPane tabbedPane = new JTabbedPane();
        parentFrame.add(tabbedPane, BorderLayout.CENTER);
        return tabbedPane;
    }

    private void addMenuBar(JFrame context){

        JMenuBar menubar = new JMenuBar();
        JMenu file, edit, preferences, fp, help;
        JMenuItem fpData, smiData;
        context.setJMenuBar(menubar);

        file = addMenuToMenuBar(menubar,MetricsConstants.P_MAIN_WINDOW_MENU_FILE);
        edit = addMenuToMenuBar(menubar,MetricsConstants.P_MAIN_WINDOW_MENU_EDIT);
        preferences = addMenuToMenuBar(menubar,MetricsConstants.P_MAIN_WINDOW_MENU_PREFERENCES);
        metrics = addMenuToMenuBar(menubar,MetricsConstants.P_MAIN_WINDOW_MENU_METRICS);
        help = addMenuToMenuBar(menubar,MetricsConstants.P_MAIN_WINDOW_MENU_HELP);
        project_code = addMenuToMenuBar(menubar,MetricsConstants.P_MAIN_WINDOW_MENU_PROJECT_CODE);
        project_code.setEnabled(false);

        addMenuItemToMenu(file,MetricsConstants.P_MENU_ITEM_FILE_NEW);
        addMenuItemToMenu(file,MetricsConstants.P_MENU_ITEM_FILE_OPEN);
        addMenuItemToMenu(file,MetricsConstants.P_MENU_ITEM_FILE_SAVE);
        addMenuItemToMenu(file,MetricsConstants.P_MENU_ITEM_FILE_EXIT);

        addMenuItemToMenu(preferences,MetricsConstants.P_MENU_ITEM_PREFERENCES_LANGUAGE);

        fp = new JMenu(MetricsConstants.P_MENU_ITEM_METRICS_FUNCTION_POINT);
        metrics.add(fp);
        this.enableMetricsMenu(false);

        fpData = new JMenuItem(MetricsConstants.P_MENU_ITEM_METRICS_ENTER_FP_DATA);
        fp.add(fpData);
        fpData.addActionListener(ActionListener_MainWindow.getInstance(context));

        smi = new JMenu(MetricsConstants.P_MENU_ITEM_METRICS_SMI);
        metrics.add(smi);
        smiData = new JMenuItem(MetricsConstants.P_MENU_ITEM_METRICS_ENTER_SMI_DATA);
        smi.add(smiData);
        smiData.addActionListener(ActionListener_MainWindow.getInstance(context));

        addProjectCode = addMenuItemToMenu(project_code,MetricsConstants.P_MENU_ITEM_PROJECT_CODE_ADD_CODE);
        projectCodeStatistics = addMenuItemToMenu(project_code,MetricsConstants.P_MENU_ITEM_METRICS_PROJECT_CODE_STATISTICS);
        toggleProjectCode(false);
    }

    /**
     * Adds Menu Item to the menu
     * @param menu JMenu
     * @param itemName String MenuItem name
     * @return JMenuItem
     */
    private JMenuItem addMenuItemToMenu(JMenu menu, String itemName){
        JMenuItem menuItem = new JMenuItem(itemName);
        menu.add(menuItem);
        menuItem.addActionListener(ActionListener_MainWindow.getInstance(this));
        return  menuItem;
    }

    /**
     * Adds Menu to Menubar
     * @param menuBar JMenubar
     * @param menuName String Menu name
     * @return JMenu
     */
    private JMenu addMenuToMenuBar(JMenuBar menuBar, String menuName){
        JMenu menu = new JMenu(menuName);
        menuBar.add(menu);
        return  menu;
    }

    public void createNewProject(){
        this.mainTabbedPane.removeAll();
        this.enableMetricsMenu(true);
        this.enableSMIMenu(true);
        project_code.setEnabled(true);
        this.revalidate();
        this.updateTree(MetricsSuite.getInstance().getProjectData());
    }

    public void updateTree(ProjectData projectData ) {
        HashMap<String,String> map = new HashMap<>();
        JTree tree = null;
        boolean addRightClickListener = false;
        if(projectData!=null){
            tree = createJTree(projectData.getProjectName());
        }else{
            tree = createJTree(MetricsConstants.P_JTREE_EMPTY);
        }

        if(projectData.getFpArray()!=null && projectData.getFpArray().size() > 0){
            addRightClickListener = true;
            for (FunctionPointData fpData:
                    projectData.getFpArray()) {
                map.put("FP-"+fpData.getTabName()+fpData.getTabName().hashCode(),fpData.getTabName());
            }}
        if(projectData.getSmiData()!=null){
            addRightClickListener = true;
            map.put("SMI-","SMI");
        }
        if(projectData.getCodeFilesArray()!=null && projectData.getCodeFilesArray().size() > 0){
            addRightClickListener = true;
            for (File codeFile:
                    projectData.getCodeFilesArray()) {
                map.put("CF-" +codeFile.getName().hashCode(),codeFile.getName());
            }
        }
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel()
                .getRoot();

        for (String value:
             map.values()) {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(value);
            model.insertNodeInto(child, root, root.getChildCount());
            tree.scrollPathToVisible(new TreePath(child.getPath()));
        }

        this.mainScrollPane.removeAll();
        this.remove(mainScrollPane);
        mainScrollPane = addJTree(this, tree,addRightClickListener);
        this.revalidate();
    }
    private JTree createJTree(String rootName){
            //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootName);
       JTree tree =  new JTree(root);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
            return tree;
    }
}
