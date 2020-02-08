package com.MetricsSuite;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private JPanel mainPanel;

    public static void main(String[] args){
        JFrame mainFrame = new JFrame("CECS 543 Metrics Suite");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(500,400);
        mainFrame.setLocation(200,100);

        // add menubar
        addMenuBar(mainFrame);

        mainFrame.setVisible(true);
    }

    private static void addMenuBar(JFrame mainFrame){
        JMenuBar menubar = new JMenuBar();
        mainFrame.setJMenuBar(menubar);

        JMenu file = new JMenu("File");
        menubar.add(file);

        JMenuItem new_menuItem = new JMenuItem("New");
        file.add(new_menuItem);

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
