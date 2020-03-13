package com.MetricsSuite.Windows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SMIWindow {
    MainWindow mainWindow;
    public JButton add_row_btn, compute_index_btn;
    public JTable smiTable;
    public DefaultTableModel model;

    public JComponent createNewSMIPanel(){
        JPanel panel = new JPanel(false);

        JLabel SMI_heading = new JLabel("Software Maturity Index");
        SMI_heading.setFont((new Font("", Font.BOLD, 15)));

        model= new DefaultTableModel();
        smiTable = new JTable(model);
        model.addColumn("SMI");
        model.addColumn("Modules Added");
        model.addColumn("Modules Changed");
        model.addColumn("Modules Deleted");
        model.addColumn("Total Module");
        JScrollPane scrollPane = new JScrollPane(smiTable);
        smiTable.setFillsViewportHeight(true);

        add_row_btn = new JButton("Add Row");
        compute_index_btn = new JButton("Compute Index");

        SMI_heading.setBounds(200,10,200,20);
        scrollPane.setBounds(20,45,540,370);
        compute_index_btn.setBounds(180, 450, 130,30);
        add_row_btn.setBounds(20, 450, 130,30);

        panel.add(SMI_heading);
        panel.add(scrollPane);
        panel.add(compute_index_btn);
        panel.add(add_row_btn);

        add_row_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addRow(new Object[]{ " ", " ", " ", " ", " " });
            }
        });

        panel.setLayout(null);
        return panel;



    }


}