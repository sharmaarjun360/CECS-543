package com.MetricsSuite.Windows;

import com.MetricsSuite.ActionListeners.ActionListener_MainWindow;
import com.MetricsSuite.MetricsSuite;
import com.MetricsSuite.GlobalConstants.MetricsConstants;
import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {

    MetricsSuite metricsSuite;

    public JTabbedPane mainTabbedPane = null;
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
//        frame.add(new JScrollPane(tree), BorderLayout.WEST);
//        addJTREE(this);
        mainTabbedPane = addTabbedPane(this);
    }
    private JTabbedPane addTabbedPane(JFrame parentFrame) {
        JTabbedPane tabbedPane = new JTabbedPane();
        parentFrame.add(tabbedPane, BorderLayout.CENTER);
        return tabbedPane;
    }

    private void addMenuBar(JFrame context){

        JMenuBar menubar = new JMenuBar();
        JMenu file, edit, preferences, metrics, fp, help;
        JMenuItem fpData;
        context.setJMenuBar(menubar);

        file = addMenuToMenuBar(menubar,MetricsConstants.P_MAIN_WINDOW_MENU_FILE);
        edit = addMenuToMenuBar(menubar,MetricsConstants.P_MAIN_WINDOW_MENU_EDIT);
        preferences = addMenuToMenuBar(menubar,MetricsConstants.P_MAIN_WINDOW_MENU_PREFERENCES);
        metrics = addMenuToMenuBar(menubar,MetricsConstants.P_MAIN_WINDOW_MENU_METRICS);
        help = addMenuToMenuBar(menubar,MetricsConstants.P_MAIN_WINDOW_MENU_HELP);

        addMenuItemToMenu(file,MetricsConstants.P_MENU_ITEM_FILE_NEW);
        addMenuItemToMenu(file,MetricsConstants.P_MENU_ITEM_FILE_OPEN);
        addMenuItemToMenu(file,MetricsConstants.P_MENU_ITEM_FILE_SAVE);
        addMenuItemToMenu(file,MetricsConstants.P_MENU_ITEM_FILE_EXIT);

        addMenuItemToMenu(preferences,MetricsConstants.P_MENU_ITEM_PREFERENCES_LANGUAGE);

        fp = new JMenu(MetricsConstants.P_MENU_ITEM_METRICS_FUNCTION_POINT);
        metrics.add(fp);
        fpData = new JMenuItem(MetricsConstants.P_MENU_ITEM_METRICS_ENTER_FP_DATA);
        fp.add(fpData);
        fpData.addActionListener(ActionListener_MainWindow.getInstance(context));
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
    }

}
