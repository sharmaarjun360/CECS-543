package com.MetricsSuite.Windows;

import com.MetricsSuite.ActionListeners.SMIActionListner;
import com.MetricsSuite.Models.SMIData;
import com.MetricsSuite.Models.SMIRowData;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SMIWindow {
    MainWindow mainWindow;
    SMIData smiData;

    public JButton add_row_btn, compute_index_btn;
    public JTable smiTable;
    public DefaultTableModel model;

    public SMIWindow(MainWindow mainWindow, boolean isSavedProject, SMIData smiData){
        if(isSavedProject){
            this.smiData = smiData;
        } else {
            SMIData smiData1 = new SMIData();
            this.smiData = smiData1;
            mainWindow.metricsSuite.getProjectData().setSmiData(smiData1);
        }

    }

    public SMIData getSmiData() {
        return smiData;
    }

    public void setSmiData(SMIData smiData) {
        this.smiData = smiData;
    }

    public JComponent createNewSMIPanel(){
        JPanel panel = new JPanel(false);

        JLabel SMI_heading = new JLabel("Software Maturity Index");
        SMI_heading.setFont((new Font("", Font.BOLD, 15)));
        SMI_heading.setHorizontalAlignment(JLabel.CENTER);

        model= new DefaultTableModel();

        smiTable = new JTable(model)
        {

            // make column non-editable
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                switch (columnIndex){
                    case 1:
                    case 2:
                    case 3:
                        return true;
                }
                return false;
            }
        };

        model.addColumn("SMI");
        model.addColumn("Modules Added");
        model.addColumn("Modules Changed");
        model.addColumn("Modules Deleted");
        model.addColumn("Total Module");
        JScrollPane scrollPane = new JScrollPane(smiTable);
        smiTable.setFillsViewportHeight(true);

        ((DefaultTableCellRenderer)smiTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        add_row_btn = new JButton("Add Row");
        compute_index_btn = new JButton("Compute Index");

        SMI_heading.setBounds(20,10,590,20);
        scrollPane.setBounds(20,45,590,370);
        compute_index_btn.setBounds(180, 450, 130,30);
        add_row_btn.setBounds(20, 450, 130,30);

        panel.add(SMI_heading);
        panel.add(scrollPane);
        panel.add(compute_index_btn);
        panel.add(add_row_btn);

        SMIActionListner listner = new SMIActionListner(this);
        add_row_btn.addActionListener(listner);
        compute_index_btn.addActionListener(listner);

        panel.setLayout(null);
        return panel;
    }

    public void updateSMITable(){

        SMIData smiData = this.smiData;
        List<SMIRowData> rows = smiData.getRowList();

        if(rows.size() == 0){
            return;
        }

        for (SMIRowData data: rows) {
            model.addRow(new Object[]{data.getSmi(), data.getModuleAdded(), data.getModuleChanged(), data.getModuleDeleted(), data.getTotalModules()});
        }

        smiData.setRowList(new ArrayList<>());

    }
}