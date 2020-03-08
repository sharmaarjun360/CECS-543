package com.MetricsSuite.Windows;

import com.MetricsSuite.ActionListeners.FunctionPointListner;
import com.MetricsSuite.Models.FunctionPointData;
import com.MetricsSuite.Models.ProjectData;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.List;

public class FunctionPointWindow {

    MainWindow mainWindow;
    FunctionPointData fpData;

    public JButton compute_fp_btn, val_adjust_btn, compute_code_size_btn, change_lang_btn;
    public JTextField ext_ip_txt, ext_op_txt, ext_inq_txt, int_lf_txt, ext_if_txt;
    public JTextField ext_ip_des_txt, ext_op_des_txt, ext_inq_des_txt, int_lf_des_txt, ext_if_des_txt;
    public JTextField total_count_des_txt, val_adj_des_txt, compute_fp_des_txt;
    public ButtonGroup ext_ip_r, ext_op_r, ext_inq_r, ext_lf_r, ext_if_r;
    public JRadioButton ext_ip_r_s, ext_ip_r_a, ext_ip_r_c;
    public JRadioButton ext_op_r_s, ext_op_r_a, ext_op_r_c;
    public JRadioButton ext_inq_r_s, ext_inq_r_a, ext_inq_r_c;
    public JRadioButton ext_lf_r_s, ext_lf_r_a, ext_lf_r_c;
    public JRadioButton ext_if_r_s, ext_if_r_a, ext_if_r_c;

    public FunctionPointWindow(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        // TODO: 06/03/20 have a look 
        this.createNewFunctionPointPanel();
    }

    public FunctionPointData getFpData() {
        return fpData;
    }

    public void setFpData(FunctionPointData fpData) {
        this.fpData = fpData;
    }

    /**
     * This Function creates FunctionPointData object and save it in projectData
     * @return
     */

    private void createFunctionPointDataObject(){
        fpData = new FunctionPointData();
        ProjectData projectData = mainWindow.metricsSuite.getProjectData();
        List<FunctionPointData> arr = projectData.getFpArray();
        arr.add(fpData);
        projectData.setFpArray(arr);
    }

    /**
     * This Function creates Empty FunctionPoint Panel
     * @return
     */
    public JComponent createNewFunctionPointPanel() {
        JPanel panel = new JPanel(false);

        //Objects of all label
        JLabel head_lbl = new JLabel("Weighting Factors");
        JLabel si_av_cpl_lbl = new JLabel("Simple    Average    Complex");
        JLabel ext_ip_lbl = new JLabel("External Inputs");
        JLabel ext_op_lbl = new JLabel("External Outputs");
        JLabel ext_inq_lbl = new JLabel("External Inquiries");
        JLabel int_lf_lbl = new JLabel("Internal Logical Files");
        JLabel ext_if_lbl = new JLabel("External Interface Files");
        JLabel total_count_lbl = new JLabel("Total Count");
        JLabel current_lang_lbl = new JLabel("Current language");

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
        ext_ip_txt = new JTextField();
        ext_op_txt = new JTextField();
        ext_inq_txt = new JTextField();
        int_lf_txt = new JTextField();
        ext_if_txt = new JTextField();
        ext_ip_des_txt = new JTextField();
        ext_op_des_txt = new JTextField();
        ext_inq_des_txt = new JTextField();
        int_lf_des_txt = new JTextField();
        ext_if_des_txt = new JTextField();
        total_count_des_txt = new JTextField();
        compute_fp_des_txt = new JTextField();
        val_adj_des_txt = new JTextField("0");

        JTextField current_lang_1_des_txt = new JTextField("Java");
        JTextField current_lang_2_des_txt = new JTextField();

        // add focus listner
        ext_ip_txt.addFocusListener(fpListner);
        ext_op_txt.addFocusListener(fpListner);
        ext_inq_txt.addFocusListener(fpListner);
        int_lf_txt.addFocusListener(fpListner);
        ext_if_txt.addFocusListener(fpListner);

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
        head_lbl.setBounds(300,5,200,30);
        head_lbl.setFont(new Font("", Font.BOLD, 15));
        si_av_cpl_lbl.setBounds(275,35,200,20);
        si_av_cpl_lbl.setFont(new Font("", Font.BOLD, 12));
        ext_ip_lbl.setBounds(10,60,150,20);
        ext_op_lbl.setBounds(10,90,150,20);
        ext_inq_lbl.setBounds(10,120,150,20);
        int_lf_lbl.setBounds(10,150,150,20);
        ext_if_lbl.setBounds(10,180,150,20);
        total_count_lbl.setBounds(10,210,150,20);
        current_lang_lbl.setBounds(205,330,150,20);

        //setting size and location of textbox
        ext_ip_txt.setBounds(160,60,50,20);
        ext_op_txt.setBounds(160,90,50,20);
        ext_inq_txt.setBounds(160,120,50,20);
        int_lf_txt.setBounds(160,150,50,20);
        ext_if_txt.setBounds(160,180,50,20);
        ext_ip_des_txt.setBounds(460,60,50,20);
        ext_op_des_txt.setBounds(460,90,50,20);
        ext_inq_des_txt.setBounds(460,120,50,20);
        int_lf_des_txt.setBounds(460,150,50,20);
        ext_if_des_txt.setBounds(460,180,50,20);
        total_count_des_txt.setBounds(460,210,50,20);
        compute_fp_des_txt.setBounds(420,250,90,20);
        val_adj_des_txt.setBounds(460,290,50,20);
        current_lang_1_des_txt.setBounds(310,330,90,20);
        current_lang_2_des_txt.setBounds(420,330,90,20);

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
        panel.add(ext_ip_txt);
        panel.add(ext_op_txt);
        panel.add(ext_inq_txt);
        panel.add(int_lf_txt);
        panel.add(ext_if_txt);
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

        //Adding Validation to text box

        PlainDocument doc_ext_ip_txt = (PlainDocument) ext_ip_txt.getDocument();
        PlainDocument doc_ext_op_txt = (PlainDocument) ext_op_txt.getDocument();
        PlainDocument doc_ext_inq_txt = (PlainDocument) ext_inq_txt.getDocument();
        PlainDocument doc_int_lf_txt = (PlainDocument) int_lf_txt.getDocument();
        PlainDocument doc_ext_if_txt = (PlainDocument) ext_if_txt.getDocument();
        doc_ext_ip_txt.setDocumentFilter(new MyIntFilter());
        doc_ext_op_txt.setDocumentFilter(new MyIntFilter());
        doc_ext_inq_txt.setDocumentFilter(new MyIntFilter());
        doc_int_lf_txt.setDocumentFilter(new MyIntFilter());
        doc_ext_if_txt.setDocumentFilter(new MyIntFilter());

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