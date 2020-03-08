package com.MetricsSuite.ActionListeners;

import com.MetricsSuite.Models.FunctionPointData;
import com.MetricsSuite.Windows.FunctionPointWindow;
import com.MetricsSuite.Windows.LanguageWindow;
import com.MetricsSuite.Windows.VAFWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Enumeration;

public class FunctionPointListner implements ActionListener, FocusListener {

    private FunctionPointWindow fpWindow;

    public FunctionPointListner(FunctionPointWindow fpWindow){
        this.fpWindow = fpWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        FunctionPointData fpData = fpWindow.getFpData();

        if(e.getSource() == fpWindow.compute_fp_btn){
            // calculate function point
            String countTotalText = fpWindow.total_count_des_txt.getText();
            String vafText = fpWindow.val_adj_des_txt.getText();

            if(countTotalText != null && countTotalText.length() > 0
                    && vafText != null && vafText.length() > 0
            ){
                long countTotal = Long.parseLong(countTotalText);
                int vafTotal = Integer.parseInt(vafText);

                fpWindow.compute_fp_des_txt.setText(Double.toString(countTotal * (0.65 + (0.01 * vafTotal))));
            } else {
                fpWindow.compute_fp_des_txt.setText(null);
            }
        }

        if(e.getSource() == fpWindow.val_adjust_btn){
            // open VAF window
            new VAFWindow(fpWindow);
        }

        if(e.getSource() == fpWindow.compute_code_size_btn){
            // TODO: 07/03/20
            // calculate code size for selected language
        }

        if(e.getSource() == fpWindow.change_lang_btn){
            // open language window
            LanguageWindow languageWindow = new LanguageWindow();
            languageWindow.setVisible(true);
        }

        if(e.getSource() == fpWindow.ext_ip_r_s
                || e.getSource() == fpWindow.ext_ip_r_a
                || e.getSource() == fpWindow.ext_ip_r_c
        ){
            this.updateInputTotal(fpWindow.txt_external_inputs, fpWindow.ext_ip_r, fpWindow.ext_ip_des_txt);

            String selectedWeightFactor = this.getSelectedButtonText(fpWindow.ext_ip_r);
            int factor = Integer.parseInt(selectedWeightFactor);
            fpData.setInputFactor(factor);
        }

        if(e.getSource() == fpWindow.ext_op_r_s
                || e.getSource() == fpWindow.ext_op_r_a
                || e.getSource() == fpWindow.ext_op_r_c
        ){
            this.updateInputTotal(fpWindow.txt_external_outputs, fpWindow.ext_op_r, fpWindow.ext_op_des_txt);

            String selectedWeightFactor = this.getSelectedButtonText(fpWindow.ext_op_r);
            int factor = Integer.parseInt(selectedWeightFactor);
            fpData.setOutputFactor(factor);
        }

        if(e.getSource() == fpWindow.ext_inq_r_s
                || e.getSource() == fpWindow.ext_inq_r_a
                || e.getSource() == fpWindow.ext_inq_r_c
        ){
            this.updateInputTotal(fpWindow.txt_external_inquiries, fpWindow.ext_inq_r, fpWindow.ext_inq_des_txt);

            String selectedWeightFactor = this.getSelectedButtonText(fpWindow.ext_inq_r);
            int factor = Integer.parseInt(selectedWeightFactor);
            fpData.setInquiryFactor(factor);
        }

        if(e.getSource() == fpWindow.ext_lf_r_s
                || e.getSource() == fpWindow.ext_lf_r_a
                || e.getSource() == fpWindow.ext_lf_r_c
        ){
            this.updateInputTotal(fpWindow.txt_Internal_logical_files, fpWindow.ext_lf_r, fpWindow.int_lf_des_txt);

            String selectedWeightFactor = this.getSelectedButtonText(fpWindow.ext_lf_r);
            int factor = Integer.parseInt(selectedWeightFactor);
            fpData.setLogicalFileFactor(factor);
        }

        if(e.getSource() == fpWindow.ext_if_r_s
                || e.getSource() == fpWindow.ext_if_r_a
                || e.getSource() == fpWindow.ext_if_r_c
        ){
            this.updateInputTotal(fpWindow.txt_external_interface_files, fpWindow.ext_if_r, fpWindow.ext_if_des_txt);

            String selectedWeightFactor = this.getSelectedButtonText(fpWindow.ext_if_r);
            int factor = Integer.parseInt(selectedWeightFactor);
            fpData.setInterfaceFileFactor(factor);
        }

        fpWindow.setFpData(fpData);
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

        FunctionPointData fpData = fpWindow.getFpData();

        if(e.getSource() == fpWindow.txt_external_inputs){
            this.updateInputTotal(fpWindow.txt_external_inputs, fpWindow.ext_ip_r, fpWindow.ext_ip_des_txt);

            String strValue = fpWindow.txt_external_inputs.getText();
            if(strValue != null && strValue.length() > 0){
                long value = Long.parseLong(strValue);
                fpData.setExternalInputCount(value);
            } else {
                fpData.setExternalInputCount(-1);
            }
        }

        if(e.getSource() == fpWindow.txt_external_outputs){
            this.updateInputTotal(fpWindow.txt_external_outputs, fpWindow.ext_op_r, fpWindow.ext_op_des_txt);

            String strValue = fpWindow.txt_external_outputs.getText();
            if(strValue != null && strValue.length() > 0){
                long value = Long.parseLong(strValue);
                fpData.setExternalOutputCount(value);
            } else {
                fpData.setExternalOutputCount(-1);
            }
        }

        if(e.getSource() == fpWindow.txt_external_inquiries){
            this.updateInputTotal(fpWindow.txt_external_inquiries, fpWindow.ext_inq_r, fpWindow.ext_inq_des_txt);

            String strValue = fpWindow.txt_external_inquiries.getText();
            if(strValue != null && strValue.length() > 0){
                long value = Long.parseLong(strValue);
                fpData.setExternalInquiriesCount(value);
            } else {
                fpData.setExternalInquiriesCount(-1);
            }
        }

        if(e.getSource() == fpWindow.txt_Internal_logical_files){
            this.updateInputTotal(fpWindow.txt_Internal_logical_files, fpWindow.ext_lf_r, fpWindow.int_lf_des_txt);
            String strValue = fpWindow.txt_Internal_logical_files.getText();
            if(strValue != null && strValue.length() > 0){
                long value = Long.parseLong(strValue);
                fpData.setInternalLogicalFileCount(value);
            } else {
                fpData.setInternalLogicalFileCount(-1);
            }
        }

        if(e.getSource() == fpWindow.txt_external_interface_files){
            this.updateInputTotal(fpWindow.txt_external_interface_files, fpWindow.ext_if_r, fpWindow.ext_if_des_txt);

            String strValue = fpWindow.txt_external_interface_files.getText();
            if(strValue != null && strValue.length() > 0){
                long value = Long.parseLong(strValue);
                fpData.setExternalInterfaceFileCount(value);
            } else {
                fpData.setExternalInterfaceFileCount(-1);
            }
        }

        fpWindow.setFpData(fpData);
    }

    private void updateInputTotal(JTextField inputField, ButtonGroup buttonGroup, JTextField outputField){
        String selectedWeightFactor = this.getSelectedButtonText(buttonGroup);
        String inputValue = inputField.getText();

        if(selectedWeightFactor != null && selectedWeightFactor.length() > 0 && inputValue != null && inputValue.length() > 0){
            int factor = Integer.parseInt(selectedWeightFactor);
            long value = Long.parseLong(inputValue);

            outputField.setText(Long.toString(factor*value));
        } else {
            outputField.setText(null);
        }
        this.computeTotalCount();
    }

    private void computeTotalCount(){

        FunctionPointData fpData = fpWindow.getFpData();

        String inputsTotal = fpWindow.ext_ip_des_txt.getText();
        String outputsTotal = fpWindow.ext_op_des_txt.getText();
        String inquiriesTotal = fpWindow.ext_inq_des_txt.getText();
        String logicalFilesTotal = fpWindow.int_lf_des_txt.getText();
        String interfaceFilesTotal = fpWindow.ext_if_des_txt.getText();

        if(inputsTotal != null && inputsTotal.length() >0
                && outputsTotal != null && outputsTotal.length() >0
                && inquiriesTotal != null && inquiriesTotal.length() >0
                && logicalFilesTotal != null && logicalFilesTotal.length() >0
                && interfaceFilesTotal != null && interfaceFilesTotal.length() >0
        ){

            long inputsTotalValue = Long.parseLong(inputsTotal);
            long outputsTotalValue = Long.parseLong(outputsTotal);
            long inquiriesTotalValue = Long.parseLong(inquiriesTotal);
            long logicalFilesTotalValue = Long.parseLong(logicalFilesTotal);
            long interfaceFilesTotalValue = Long.parseLong(interfaceFilesTotal);

            fpWindow.total_count_des_txt.setText(Long.toString(inputsTotalValue+outputsTotalValue+inquiriesTotalValue+logicalFilesTotalValue+interfaceFilesTotalValue));
        } else {
            fpWindow.total_count_des_txt.setText(null);
        }

        fpWindow.setFpData(fpData);
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
