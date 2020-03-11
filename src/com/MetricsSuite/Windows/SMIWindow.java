package com.MetricsSuite.Windows;

import javax.swing.*;
import java.awt.*;

public class SMIWindow  extends JFrame{
    MainWindow mainWindow;
    public String[] columnNames = {"SMI",
            "Module Added",
            "Modules Changed",
            "Modules Deleted",
            "Total Module"};
    public Object[][] data = {
    };

    public JComponent createNewSMIPanel(){
        JPanel panel = new JPanel(false);

        JLabel SMI_heading = new JLabel("Software Maturity Index");
        SMI_heading.setFont((new Font("", Font.BOLD, 15)));

        JTable smiTable = new JTable(data,columnNames);
        JScrollPane scrollPane = new JScrollPane(smiTable);
        smiTable.setFillsViewportHeight(true);

        JButton add_row_btn = new JButton("Add Row");
        JButton compute_index_btn = new JButton("Compute Index");

        SMI_heading.setBounds(200,5,200,20);
        scrollPane.setBounds(20,50,540,360);
        compute_index_btn.setBounds(180, 450, 130,20);
        add_row_btn.setBounds(20, 450, 130,20);

        panel.add(SMI_heading);
        panel.add(scrollPane);
        panel.add(compute_index_btn);
        panel.add(add_row_btn);

        panel.setLayout(null);
        return panel;

    }


}