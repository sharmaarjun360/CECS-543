package com.MetricsSuite.Windows;

import com.MetricsSuite.ActionListeners.FunctionPointListner;
import com.MetricsSuite.Alert.MetricsAlert;
import com.MetricsSuite.GlobalConstants.MetricsConstants;
import com.MetricsSuite.Models.FunctionPointData;
import com.MetricsSuite.Models.ProjectData;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class FunctionPointWindow {

    MainWindow mainWindow;
    FunctionPointData fpData;

    public JButton compute_fp_btn, val_adjust_btn, compute_code_size_btn, change_lang_btn;
    public JTextField txt_external_inputs, txt_external_outputs, txt_external_inquiries, txt_Internal_logical_files, txt_external_interface_files,txt_total_code_size;
    // TODO: 08/03/20 change to label if possible
    public JTextField ext_ip_des_txt, ext_op_des_txt, ext_inq_des_txt, int_lf_des_txt, ext_if_des_txt;
    public JTextField total_count_des_txt, val_adj_des_txt, compute_fp_des_txt;
    public ButtonGroup ext_ip_r, ext_op_r, ext_inq_r, ext_lf_r, ext_if_r;
    public JRadioButton ext_ip_r_s, ext_ip_r_a, ext_ip_r_c;
    public JRadioButton ext_op_r_s, ext_op_r_a, ext_op_r_c;
    public JRadioButton ext_inq_r_s, ext_inq_r_a, ext_inq_r_c;
    public JRadioButton ext_lf_r_s, ext_lf_r_a, ext_lf_r_c;
    public JRadioButton ext_if_r_s, ext_if_r_a, ext_if_r_c;
    public JTextField current_lang_1_des_txt, current_lang_2_des_txt;

    public FunctionPointWindow(MainWindow mainWindow, boolean isSavedProject, FunctionPointData fpData, String tabName){
        this.mainWindow = mainWindow;
        // TODO: 06/03/20 have a look
//        this.createNewFunctionPointPanel();
        this.createFunctionPointDataObject(isSavedProject, fpData, tabName);
    }

    public FunctionPointData getFpData() {
        return fpData;
    }

    /***
     * This method Saves Function point window data to function Point, it is called every time an action is performed on Function point window.
     * @param fpData
     */
    public void setFpData(FunctionPointData fpData) {
        this.fpData = fpData;
    }

    /**
     * This Function creates FunctionPointData object and save it in projectData
     * @return
     */

    private void createFunctionPointDataObject(boolean isSavedProject, FunctionPointData fpData, String tabName){

        ProjectData projectData = mainWindow.metricsSuite.getProjectData();
        if(projectData == null){
            MetricsAlert.getInstance().showAlert(this.mainWindow, MetricsConstants.P_ALERT_CREATE_PROJECT);
            return;
        }
        if(!isSavedProject){
            this.fpData = new FunctionPointData(tabName, mainWindow.metricsSuite.getSelectedLanguage(), mainWindow.metricsSuite.getLanguageCodeSize());
            projectData.getFpArray().add(this.fpData);
        } else {
            this.fpData = fpData;
        }
    }

    /**
     * This Function creates Empty FunctionPoint Panel
     * @return
     */
    public JComponent createNewFunctionPointPanel() {
        JPanel panel = new JPanel(false);

        //Objects of all label
        JLabel head_lbl = new JLabel(MetricsConstants.WEIGHTING_FACTORS);
        JLabel si_av_cpl_lbl = new JLabel(MetricsConstants.SIMPLE_AVERAGE_COMPLEX);
        JLabel ext_ip_lbl = new JLabel(MetricsConstants.EXTERNAL_INPUTS);
        JLabel ext_op_lbl = new JLabel(MetricsConstants.EXTERNAL_OUTPUTS);
        JLabel ext_inq_lbl = new JLabel(MetricsConstants.EXTERNAL_INQUIRIES);
        JLabel int_lf_lbl = new JLabel(MetricsConstants.INTERNAL_LOGICAL_FILES);
        JLabel ext_if_lbl = new JLabel(MetricsConstants.EXTERNAL_INTERFACE_FILES);
        JLabel total_count_lbl = new JLabel(MetricsConstants.TOTAL_COUNT);
        JLabel current_lang_lbl = new JLabel(MetricsConstants.CURRENT_LANGUAGE);

        // Objects of all buttons
        compute_fp_btn = new JButton("Compute FP");
        val_adjust_btn = new JButton("Value Adujstment");
        compute_code_size_btn = new JButton("Compute Code Size");
        change_lang_btn = new JButton("Change Language");

        // add action listner
        FunctionPointListner fpListner = new FunctionPointListner(this);
        compute_fp_btn.addActionListener(fpListner);
        val_adjust_btn.addActionListener(fpListner);
        compute_code_size_btn.addActionListener(fpListner);
        change_lang_btn.addActionListener(fpListner);

        //objects of radio button
        ext_ip_r_s = new JRadioButton("3");
        ext_ip_r_a = new JRadioButton("4");
        ext_ip_r_c = new JRadioButton("6");
        ext_ip_r = new ButtonGroup();
        ext_ip_r.add(ext_ip_r_s);
        ext_ip_r.add(ext_ip_r_a);
        ext_ip_r.add(ext_ip_r_c);
        // add action listner
        ext_ip_r_s.addActionListener(fpListner);
        ext_ip_r_a.addActionListener(fpListner);
        ext_ip_r_c.addActionListener(fpListner);

        ext_op_r_s = new JRadioButton("4");
        ext_op_r_a = new JRadioButton("5");
        ext_op_r_c = new JRadioButton("7");
        ext_op_r = new ButtonGroup();
        ext_op_r.add(ext_op_r_s);
        ext_op_r.add(ext_op_r_a);
        ext_op_r.add(ext_op_r_c);
        // add action listner
        ext_op_r_s.addActionListener(fpListner);
        ext_op_r_a.addActionListener(fpListner);
        ext_op_r_c.addActionListener(fpListner);


        ext_inq_r_s = new JRadioButton("3");
        ext_inq_r_a = new JRadioButton("4");
        ext_inq_r_c = new JRadioButton("6");
        ext_inq_r = new ButtonGroup();
        ext_inq_r.add(ext_inq_r_s);
        ext_inq_r.add(ext_inq_r_a);
        ext_inq_r.add(ext_inq_r_c);
        // add action listner
        ext_inq_r_s.addActionListener(fpListner);
        ext_inq_r_a.addActionListener(fpListner);
        ext_inq_r_c.addActionListener(fpListner);

        ext_lf_r_s = new JRadioButton("7");
        ext_lf_r_a = new JRadioButton("10");
        ext_lf_r_c = new JRadioButton("15");
        ext_lf_r = new ButtonGroup();
        ext_lf_r.add(ext_lf_r_s);
        ext_lf_r.add(ext_lf_r_a);
        ext_lf_r.add(ext_lf_r_c);
        // add action listner
        ext_lf_r_s.addActionListener(fpListner);
        ext_lf_r_a.addActionListener(fpListner);
        ext_lf_r_c.addActionListener(fpListner);

        ext_if_r_s = new JRadioButton("5");
        ext_if_r_a = new JRadioButton("7");
        ext_if_r_c = new JRadioButton("10");
        ext_if_r = new ButtonGroup();
        ext_if_r.add(ext_if_r_s);
        ext_if_r.add(ext_if_r_a);
        ext_if_r.add(ext_if_r_c);
        // add action listner
        ext_if_r_s.addActionListener(fpListner);
        ext_if_r_a.addActionListener(fpListner);
        ext_if_r_c.addActionListener(fpListner);


        //objects of text boxes
        txt_external_inputs = new JTextField();
        txt_external_outputs = new JTextField();
        txt_external_inquiries = new JTextField();
        txt_Internal_logical_files = new JTextField();
        txt_external_interface_files = new JTextField();
        ext_ip_des_txt = new JTextField();
        ext_op_des_txt = new JTextField();
        ext_inq_des_txt = new JTextField();
        int_lf_des_txt = new JTextField();
        ext_if_des_txt = new JTextField();
        total_count_des_txt = new JTextField();
        compute_fp_des_txt = new JTextField();
        val_adj_des_txt = new JTextField("0");
        current_lang_1_des_txt = new JTextField(fpData.getSelectedLanguage());
        current_lang_2_des_txt = new JTextField();

        PlainDocument doc_ext_ip_txt = (PlainDocument) txt_external_inputs.getDocument();
        PlainDocument doc_ext_op_txt = (PlainDocument) txt_external_outputs.getDocument();
        PlainDocument doc_ext_inq_txt = (PlainDocument) txt_external_inquiries.getDocument();
        PlainDocument doc_int_lf_txt = (PlainDocument) txt_Internal_logical_files.getDocument();
        PlainDocument doc_ext_if_txt = (PlainDocument) txt_external_interface_files.getDocument();
        doc_ext_ip_txt.setDocumentFilter(new MyIntFilter());
        doc_ext_op_txt.setDocumentFilter(new MyIntFilter());
        doc_ext_inq_txt.setDocumentFilter(new MyIntFilter());
        doc_int_lf_txt.setDocumentFilter(new MyIntFilter());
        doc_ext_if_txt.setDocumentFilter(new MyIntFilter());

        // add focus listner
        txt_external_inputs.addFocusListener(fpListner);
        txt_external_outputs.addFocusListener(fpListner);
        txt_external_inquiries.addFocusListener(fpListner);
        txt_Internal_logical_files.addFocusListener(fpListner);
        txt_external_interface_files.addFocusListener(fpListner);

        // Disable text box
        ext_ip_des_txt.setEditable(false);
        ext_op_des_txt.setEditable(false);
        ext_inq_des_txt.setEditable(false);
        int_lf_des_txt.setEditable(false);
        ext_if_des_txt.setEditable(false);
        total_count_des_txt.setEditable(false);
        compute_fp_des_txt.setEditable(false);
        val_adj_des_txt.setEditable(false);
        current_lang_1_des_txt.setEditable(false);
        current_lang_2_des_txt.setEditable(false);

        // Setting location and size of label
        head_lbl.setBounds(MetricsConstants.head_lbl_X, MetricsConstants.head_lbl_Y, MetricsConstants.head_lbl_WIDTH,30);
        head_lbl.setFont(new Font("", Font.BOLD, 15));
        si_av_cpl_lbl.setBounds(275,35, MetricsConstants.si_av_cpl_lbl_WIDTH,20);
        si_av_cpl_lbl.setFont(new Font("", Font.BOLD, 12));
        ext_ip_lbl.setBounds(10,60,150,20);
        ext_op_lbl.setBounds(10,90,150,20);
        ext_inq_lbl.setBounds(10,120,150,20);
        int_lf_lbl.setBounds(10,150,150,20);
        ext_if_lbl.setBounds(10,180,150,20);
        total_count_lbl.setBounds(10,210,150,20);
        current_lang_lbl.setBounds(205,330,150,20);

        //setting size and location of textbox
        txt_external_inputs.setBounds(160,60,50,20);
        txt_external_outputs.setBounds(160,90,50,20);
        txt_external_inquiries.setBounds(160,120,50,20);
        txt_Internal_logical_files.setBounds(160,150,50,20);
        txt_external_interface_files.setBounds(160,180,50,20);
        ext_ip_des_txt.setBounds(460,60,85,20);
        ext_op_des_txt.setBounds(460,90,85,20);
        ext_inq_des_txt.setBounds(460,120,85,20);
        int_lf_des_txt.setBounds(460,150,85,20);
        ext_if_des_txt.setBounds(460,180,85,20);
        total_count_des_txt.setBounds(460,210,85,20);
        compute_fp_des_txt.setBounds(430,250,115,20);
        val_adj_des_txt.setBounds(495,290,50,20);
        current_lang_1_des_txt.setBounds(315,330,100,20);
        current_lang_2_des_txt.setBounds(430,330,115,20);

        //setting size and location of radiobutton
        ext_ip_r_s.setBounds(285,60,50,20);
        ext_ip_r_a.setBounds(340,60,50,20);
        ext_ip_r_c.setBounds(400,60,50,20);
        ext_op_r_s.setBounds(285,90,50,20);
        ext_op_r_a.setBounds(340,90,50,20);
        ext_op_r_c.setBounds(400,90,50,20);
        ext_inq_r_s.setBounds(285,120,50,20);
        ext_inq_r_a.setBounds(340,120,50,20);
        ext_inq_r_c.setBounds(400,120,50,20);
        ext_lf_r_s.setBounds(285,150,50,20);
        ext_lf_r_a.setBounds(340,150,50,20);
        ext_lf_r_c.setBounds(400,150,50,20);
        ext_if_r_s.setBounds(285,180,50,20);
        ext_if_r_a.setBounds(340,180,50,20);
        ext_if_r_c.setBounds(400,180,50,20);

        //setting size and location of buttons
        compute_fp_btn.setBounds(10,250,150,30);
        val_adjust_btn.setBounds(10,290,150,30);
        compute_code_size_btn.setBounds(10,330,150,30);
        change_lang_btn.setBounds(10,370,150,30);


        //adding labels to panel
        panel.add(head_lbl);
        panel.add(si_av_cpl_lbl);
        panel.add(ext_ip_lbl);
        panel.add(ext_op_lbl);
        panel.add(ext_inq_lbl);
        panel.add(int_lf_lbl);
        panel.add(ext_if_lbl);
        panel.add(total_count_lbl);
        panel.add(current_lang_lbl);

        //adding text boxes to panel
        panel.add(txt_external_inputs);
        panel.add(txt_external_outputs);
        panel.add(txt_external_inquiries);
        panel.add(txt_Internal_logical_files);
        panel.add(txt_external_interface_files);
        panel.add(ext_ip_des_txt);
        panel.add(ext_op_des_txt);
        panel.add(ext_inq_des_txt);
        panel.add(int_lf_des_txt);
        panel.add(ext_if_des_txt);
        panel.add(total_count_des_txt);
        panel.add(compute_fp_des_txt);
        panel.add(val_adj_des_txt);
        panel.add(current_lang_1_des_txt);
        panel.add(current_lang_2_des_txt);

        //adding radio buttons to panel
        panel.add(ext_ip_r_s);
        panel.add(ext_ip_r_a);
        panel.add(ext_ip_r_c);
        panel.add(ext_op_r_s);
        panel.add(ext_op_r_a);
        panel.add(ext_op_r_c);
        panel.add(ext_inq_r_s);
        panel.add(ext_inq_r_a);
        panel.add(ext_inq_r_c);
        panel.add(ext_lf_r_s);
        panel.add(ext_lf_r_a);
        panel.add(ext_lf_r_c);
        panel.add(ext_if_r_s);
        panel.add(ext_if_r_a);
        panel.add(ext_if_r_c);

        //adding buttons to panel
        panel.add(compute_fp_btn);
        panel.add(val_adjust_btn);
        panel.add(compute_code_size_btn);
        panel.add(change_lang_btn);

        panel.setLayout(null);
        return panel;
    }

}

class MyIntFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string,
                             AttributeSet attr) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, string);

        if (test(sb.toString())) {
            super.insertString(fb, offset, string, attr);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    private boolean test(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
                        AttributeSet attrs) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, text);

        if (test(sb.toString())) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }

    }
}