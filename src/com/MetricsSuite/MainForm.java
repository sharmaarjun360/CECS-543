package com.MetricsSuite;

import javax.swing.*;
import java.awt.*;

public class MainForm {

    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("MetricSuit");
        mainFrame.setContentPane(new MainForm().mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(400,400);
        mainFrame.setLocation((screenSize.width)/2,(screenSize.height)/2);
//        mainFrame.pack(); packs according to components size
        mainFrame.setVisible(true);
    }
}
