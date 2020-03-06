package com.MetricsSuite.ActionListeners;

import com.MetricsSuite.Windows.FunctionPointWindow;
import com.MetricsSuite.Windows.LanguageWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionPointListner implements ActionListener {

    private FunctionPointWindow fpWindow;

    public FunctionPointListner(FunctionPointWindow fpWindow){
        this.fpWindow = fpWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.fpWindow.compute_fp_btn){
            // calculate function point
        }

        if(e.getSource() == this.fpWindow.val_adjust_btn){
            // open VAF window
        }

        if(e.getSource() == this.fpWindow.compute_code_size_btn){
            // calculate code size for selected language
        }

        if(e.getSource() == this.fpWindow.change_lang_btn){
            // open language window
            LanguageWindow languageWindow = new LanguageWindow();
            languageWindow.setVisible(true);
        }
    }
}
