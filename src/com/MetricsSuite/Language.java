package com.MetricsSuite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Language extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel mainLabel;

    public Language(){
        container.setLayout(null);
        initComponent();
        setSize(400,360);
        setLocation(300,200);
        setTitle("Language");
    }

    public void initComponent() {

        mainLabel = new JLabel("Please select one Language");
        mainLabel.setBounds(0,20,400,20);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(mainLabel);

        JPanel languagePanel = new JPanel();
        languagePanel.setLayout(new GridLayout(10,2));
        languagePanel.setBounds(10,50,400,300);

        ButtonGroup group = new ButtonGroup();

        JCheckBox c = new JCheckBox("C");
        group.add(c);

        JCheckBox cPlus = new JCheckBox("C++");
        group.add(cPlus);

        languagePanel.add(c);
        languagePanel.add(cPlus);
        container.add(languagePanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
