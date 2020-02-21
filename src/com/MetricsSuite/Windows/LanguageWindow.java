package com.MetricsSuite.Windows;

import com.MetricsSuite.Models.LanguagePreference;
import com.MetricsSuite.GlobalConstants.MetricsConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguageWindow extends JFrame implements ActionListener {

    private Container container = getContentPane();
    private JLabel mainLabel;

    LanguagePreference[] language = new LanguagePreference[12];

    public LanguageWindow(){
        initilizeData();
        container.setLayout(null);
        initComponent();
        setSize(MetricsConstants.LANGUAGE_WINDOW_WIDTH,MetricsConstants.LANGUAGE_WINDOW_HEIGHT);
        setLocation(MetricsConstants.LANGUAGE_WINDOW_LOCATION_X,MetricsConstants.LANGUAGE_WINDOW_LOCATION_Y);
        setTitle("Language");
    }

    private void initilizeData(){

        // TODO: re-factor this code
        int order = 1;
        language[order-1] = new LanguagePreference("Assembler", 50, order);
        order++;
        language[order-1] = new LanguagePreference("Ada 95", 50, order);
        order++;
        language[order-1] = new LanguagePreference("C", 50, order);
        order++;
        language[order-1] = new LanguagePreference("C++", 50, order);
        order++;
        language[order-1] = new LanguagePreference("C#", 50, order);
        order++;
        language[order-1] = new LanguagePreference("COBOL", 50, order);
        order++;
        language[order-1] = new LanguagePreference("FORTRAN", 50, order);
        order++;
        language[order-1] = new LanguagePreference("HTML", 50, order);
        order++;
        language[order-1] = new LanguagePreference("Java", 50, order);
        order++;
        language[order-1] = new LanguagePreference("Javascript", 50, order);
        order++;
        language[order-1] = new LanguagePreference("VBScript", 50, order);
        order++;
        language[order-1] = new LanguagePreference("Visual Basic", 50, order);

    }

    public void initComponent() {

        mainLabel = new JLabel("Select one Language");
        mainLabel.setBounds(15,20,200,10);
        container.add(mainLabel);

        JPanel languagePanel = new JPanel();
        languagePanel.setLayout(new GridLayout(13,1));
        languagePanel.setBounds(10,30,120,350);

        ButtonGroup group = new ButtonGroup();

        for (int i=0; i< language.length;i++){

            JCheckBox lCheckbox = new JCheckBox(language[i].name);
            group.add(lCheckbox);
            languagePanel.add(lCheckbox);
        }

        JButton doneButton = new JButton("Done");
        languagePanel.add(doneButton);
        container.add(languagePanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
