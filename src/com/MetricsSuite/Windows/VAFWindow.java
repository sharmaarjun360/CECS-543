package com.MetricsSuite.Windows;

import com.MetricsSuite.Models.FunctionPointData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VAFWindow extends JFrame {
    public JButton done_btn, cancel_btn;
    private Container container = getContentPane();
    List<JComboBox<Integer>> comboboxArr = new ArrayList<>();

    FunctionPointWindow fpWindow;

    Integer[] vafValueOption = {0, 1, 2, 3, 4, 5};

    public  VAFWindow(FunctionPointWindow fpWindow){

        this.fpWindow = fpWindow;

        setTitle("Set VAF values");
        container.setLayout(null);
        setVisible(true);
        setSize(720,600);

        done_btn = new JButton("Done");
        cancel_btn = new JButton("Cancel");

        JLabel heading_lbl = new JLabel("Assign a value from 0 to 5 for each of the following Value Adjustment Factor");
        JLabel VAF1_lbl = new JLabel("Does the system require reliable backup and recovery option?");
        JLabel VAF2_lbl = new JLabel("Are specialized data communications required to transfer information to or from the application?");
        JLabel VAF3_lbl = new JLabel("Are there distributed processing function?");
        JLabel VAF4_lbl = new JLabel("Is performance critical?");
        JLabel VAF5_lbl = new JLabel("Will the system run in an existing, heavily utilized operational environment?");
        JLabel VAF6_lbl = new JLabel("Does the system require online data entry?");
        JLabel VAF7_lbl = new JLabel("Does the online data entry require the input transaction to be built over multiple screens or operations?");
        JLabel VAF8_lbl = new JLabel("Are the internal logical files updated online?");
        JLabel VAF9_lbl = new JLabel("Are the input, output file or inquiries complex?");
        JLabel VAF10_lbl = new JLabel("Is the internal processing complex?");
        JLabel VAF11_lbl = new JLabel("Is the code designed to be reusable?");
        JLabel VAF12_lbl = new JLabel("Are the conversion and installation included in the design?");
        JLabel VAF13_lbl = new JLabel("Is the system design ofr multiple installation in different organizations?");
        JLabel VAF14_lbl = new JLabel("Is the application designed to facilitate change and for ease of use by the user?");

        heading_lbl.setBounds(5,5,500,20);
        VAF1_lbl.setBounds(5,35,600,20);
        VAF2_lbl.setBounds(5,65,600,20);
        VAF3_lbl.setBounds(5,95,600,20);
        VAF4_lbl.setBounds(5,125,600,20);
        VAF5_lbl.setBounds(5,155,600,20);
        VAF6_lbl.setBounds(5,185,600,20);
        VAF7_lbl.setBounds(5,215,600,20);
        VAF8_lbl.setBounds(5,245,600,20);
        VAF9_lbl.setBounds(5,275,600,20);
        VAF10_lbl.setBounds(5,305,600,20);
        VAF11_lbl.setBounds(5,335,600,20);
        VAF12_lbl.setBounds(5,365,600,20);
        VAF13_lbl.setBounds(5,395,600,20);
        VAF14_lbl.setBounds(5,425,600,20);

        int[] vafValueArr = fpWindow.getFpData().getVafValue();

        for(int i =0 ; i< 14; i++){
            JComboBox<Integer> cmb = new JComboBox<Integer>(vafValueOption);
            cmb.setBounds(640,35 + (i*30),60,20);
            cmb.setSelectedIndex(vafValueArr[i]);
            add(cmb);
            comboboxArr.add(i,cmb);
        }

        done_btn.setBounds(5,490,80,20);
        cancel_btn.setBounds(95,490,80,20);

        add(heading_lbl);
        add(VAF1_lbl);
        add(VAF2_lbl);
        add(VAF3_lbl);
        add(VAF4_lbl);
        add(VAF5_lbl);
        add(VAF6_lbl);
        add(VAF7_lbl);
        add(VAF8_lbl);
        add(VAF9_lbl);
        add(VAF10_lbl);
        add(VAF11_lbl);
        add(VAF12_lbl);
        add(VAF13_lbl);
        add(VAF14_lbl);

        add(done_btn);
        add(cancel_btn);

        done_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FunctionPointData fpData = fpWindow.getFpData();

                int[] vafValueArr = fpData.getVafValue();
                int vaf = 0;

                for(int i =0; i<14;i++){
                    vafValueArr[i] = (int) comboboxArr.get(i).getSelectedItem();
                    vaf += vafValueArr[i];
                }

                fpWindow.val_adj_des_txt.setText(Integer.toString(vaf));
                fpData.setVafTotal(vaf);
                fpData.setVafValue(vafValueArr);

                fpWindow.setFpData(fpData);
                dispose();
            }
        });

        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
}
