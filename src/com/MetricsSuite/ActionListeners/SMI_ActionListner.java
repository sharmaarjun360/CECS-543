package com.MetricsSuite.ActionListeners;

import com.MetricsSuite.Windows.SMIWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SMI_ActionListner implements ActionListener{

    private SMIWindow smiWindow;

    public void getSmiWindow(SMIWindow smiWindow) {
        this.smiWindow = smiWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == smiWindow.add_row_btn){
            smiWindow.model.addRow(new Object[] { " ", " ", " ", " ", " " });
        }

    }
}
