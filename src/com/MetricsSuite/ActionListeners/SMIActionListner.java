package com.MetricsSuite.ActionListeners;

import com.MetricsSuite.Models.SMIData;
import com.MetricsSuite.Models.SMIRowData;
import com.MetricsSuite.Windows.SMIWindow;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SMIActionListner implements ActionListener{

    private SMIWindow smiWindow;

    public SMIActionListner(SMIWindow smiWindow){
        this.smiWindow = smiWindow;
    }

    public void getSmiWindow(SMIWindow smiWindow) {
        this.smiWindow = smiWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == smiWindow.add_row_btn){
            // fix this
            Object[] obj = new Object[] { " ", " ", " ", " ", " " };
            smiWindow.model.addRow(obj);
        } else if(e.getSource() == smiWindow.compute_index_btn){
            DefaultTableModel model = smiWindow.model;

            int rowCount = model.getRowCount(), cell1, cell2, cell3, totalModules, prevTotal = 0;
            double smi;
            String str;
            SMIData smiData = smiWindow.getSmiData();
            List<SMIRowData> rows = smiData.getRowList();


            for(int i = 0; i< rowCount; i++){
                System.out.println("\nFor row = "+i);
                str = model.getValueAt(i,1).toString().trim();
                System.out.println("cell1 value: "+str);
                if(str == null || str.length() == 0){
                    continue;
                }
                cell1 = Integer.parseInt(str);

                str = model.getValueAt(i,2).toString().trim();
                System.out.println("cell2 value: "+str);
                if(str == null || str.length() == 0){
                    continue;
                }
                cell2 = Integer.parseInt(str);

                str = model.getValueAt(i,3).toString().trim();
                System.out.println("cell3 value: "+str);
                if(str == null || str.length() == 0){
                    continue;
                }
                cell3 = Integer.parseInt(str);

                if(cell1 >= 0 && cell2 >= 0 && cell3 >= 0){
                    totalModules = prevTotal + cell1 - cell3;
                    model.setValueAt(totalModules, i, 4);
                    smi = (double) (totalModules - (cell1+cell2+cell3))/totalModules;
                    model.setValueAt(smi, i, 0);
                    prevTotal = totalModules;

                    SMIRowData rowData = new SMIRowData(smi, cell1, cell2, cell3, totalModules);
                    rows.add(rowData);
                }
            }

            System.out.println(rows.toString());
            smiData.setRowList(rows);
            smiWindow.setSmiData(smiData);
        }

    }
}
