package com.MetricsSuite.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VAFWindow extends JFrame {
    public JButton done_btn, cancel_btn;
    private Container container = getContentPane();

    public  VAFWindow(){
        setTitle("Set VAF values");
        container.setLayout(null);

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

        JComboBox<String> VAF1_cmb = new JComboBox<String>();
        VAF1_cmb.addItem("1");
        VAF1_cmb.addItem("2");
        VAF1_cmb.addItem("3");
        VAF1_cmb.addItem("4");
        VAF1_cmb.addItem("5");
        JComboBox<String> VAF2_cmb = new JComboBox<String>();
        VAF2_cmb.addItem("1");
        VAF2_cmb.addItem("2");
        VAF2_cmb.addItem("3");
        VAF2_cmb.addItem("4");
        VAF2_cmb.addItem("5");
        JComboBox<String> VAF3_cmb = new JComboBox<String>();
        VAF3_cmb.addItem("1");
        VAF3_cmb.addItem("2");
        VAF3_cmb.addItem("3");
        VAF3_cmb.addItem("4");
        VAF3_cmb.addItem("5");
        JComboBox<String> VAF4_cmb = new JComboBox<String>();
        VAF4_cmb.addItem("1");
        VAF4_cmb.addItem("2");
        VAF4_cmb.addItem("3");
        VAF4_cmb.addItem("4");
        VAF4_cmb.addItem("5");
        JComboBox<String> VAF5_cmb = new JComboBox<String>();
        VAF5_cmb.addItem("1");
        VAF5_cmb.addItem("2");
        VAF5_cmb.addItem("3");
        VAF5_cmb.addItem("4");
        VAF5_cmb.addItem("5");
        JComboBox<String> VAF6_cmb = new JComboBox<String>();
        VAF6_cmb.addItem("1");
        VAF6_cmb.addItem("2");
        VAF6_cmb.addItem("3");
        VAF6_cmb.addItem("4");
        VAF6_cmb.addItem("5");
        JComboBox<String> VAF7_cmb = new JComboBox<String>();
        VAF7_cmb.addItem("1");
        VAF7_cmb.addItem("2");
        VAF7_cmb.addItem("3");
        VAF7_cmb.addItem("4");
        VAF7_cmb.addItem("5");
        JComboBox<String> VAF8_cmb = new JComboBox<String>();
        VAF8_cmb.addItem("1");
        VAF8_cmb.addItem("2");
        VAF8_cmb.addItem("3");
        VAF8_cmb.addItem("4");
        VAF8_cmb.addItem("5");
        JComboBox<String> VAF9_cmb = new JComboBox<String>();
        VAF9_cmb.addItem("1");
        VAF9_cmb.addItem("2");
        VAF9_cmb.addItem("3");
        VAF9_cmb.addItem("4");
        VAF9_cmb.addItem("5");
        JComboBox<String> VAF10_cmb = new JComboBox<String>();
        VAF10_cmb.addItem("1");
        VAF10_cmb.addItem("2");
        VAF10_cmb.addItem("3");
        VAF10_cmb.addItem("4");
        VAF10_cmb.addItem("5");
        JComboBox<String> VAF11_cmb = new JComboBox<String>();
        VAF11_cmb.addItem("1");
        VAF11_cmb.addItem("2");
        VAF11_cmb.addItem("3");
        VAF11_cmb.addItem("4");
        VAF11_cmb.addItem("5");
        JComboBox<String> VAF12_cmb = new JComboBox<String>();
        VAF12_cmb.addItem("1");
        VAF12_cmb.addItem("2");
        VAF12_cmb.addItem("3");
        VAF12_cmb.addItem("4");
        VAF12_cmb.addItem("5");
        JComboBox<String> VAF13_cmb = new JComboBox<String>();
        VAF13_cmb.addItem("1");
        VAF13_cmb.addItem("2");
        VAF13_cmb.addItem("3");
        VAF13_cmb.addItem("4");
        VAF13_cmb.addItem("5");
        JComboBox<String> VAF14_cmb = new JComboBox<String>();
        VAF14_cmb.addItem("1");
        VAF14_cmb.addItem("2");
        VAF14_cmb.addItem("3");
        VAF14_cmb.addItem("4");
        VAF14_cmb.addItem("5");

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

        VAF1_cmb.setBounds(640,35,40,20);
        VAF2_cmb.setBounds(640,65,40,20);
        VAF3_cmb.setBounds(640,95,40,20);
        VAF4_cmb.setBounds(640,125,40,20);
        VAF5_cmb.setBounds(640,155,40,20);
        VAF6_cmb.setBounds(640,185,40,20);
        VAF7_cmb.setBounds(640,215,40,20);
        VAF8_cmb.setBounds(640,245,40,20);
        VAF9_cmb.setBounds(640,275,40,20);
        VAF10_cmb.setBounds(640,305,40,20);
        VAF11_cmb.setBounds(640,335,40,20);
        VAF12_cmb.setBounds(640,365,40,20);
        VAF13_cmb.setBounds(640,395,40,20);
        VAF14_cmb.setBounds(640,425,40,20);

        done_btn.setBounds(5,490,80,20);
        cancel_btn.setBounds(95,490,80,20);
        done_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


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

        add(VAF1_cmb);
        add(VAF2_cmb);
        add(VAF3_cmb);
        add(VAF4_cmb);
        add(VAF5_cmb);
        add(VAF6_cmb);
        add(VAF7_cmb);
        add(VAF8_cmb);
        add(VAF9_cmb);
        add(VAF10_cmb);
        add(VAF11_cmb);
        add(VAF12_cmb);
        add(VAF13_cmb);
        add(VAF14_cmb);

        add(done_btn);
        add(cancel_btn);


        setVisible(true);
        setSize(700,600);

    }
}
