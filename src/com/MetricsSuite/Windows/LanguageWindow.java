package com.MetricsSuite.Windows;

import com.MetricsSuite.Alert.MetricsAlert;
import com.MetricsSuite.MetricsSuite;
import com.MetricsSuite.Models.FunctionPointData;
import com.MetricsSuite.Models.LanguagePreference;
import com.MetricsSuite.GlobalConstants.MetricsConstants;
import com.MetricsSuite.Models.ProjectData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LanguageWindow extends JFrame implements ActionListener {

    FunctionPointWindow fpWindow;
    MetricsSuite metricsSuite;
    boolean isDefaultLanguageWindow;
    private Container container = getContentPane();
    private JLabel mainLabel;
    List<JCheckBox> lChechboxArr = new ArrayList<>();
    JButton doneButton;

    LanguagePreference[] language = new LanguagePreference[12];

    public LanguageWindow(FunctionPointWindow fpWindow){
        isDefaultLanguageWindow = false;
        this.fpWindow = fpWindow;
        initilizeData();
        container.setLayout(null);
        initComponent();
        setSize(MetricsConstants.LANGUAGE_WINDOW_WIDTH,MetricsConstants.LANGUAGE_WINDOW_HEIGHT);
        setLocation(MetricsConstants.LANGUAGE_WINDOW_LOCATION_X,MetricsConstants.LANGUAGE_WINDOW_LOCATION_Y);
        setTitle("Language");
    }

    public LanguageWindow(MainWindow mainWindow){
        isDefaultLanguageWindow = true;
        this.metricsSuite = mainWindow.metricsSuite;
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
        language[order-1] = new LanguagePreference("Assembler", 209, order);
        order++;
        language[order-1] = new LanguagePreference("Ada 95", 154, order);
        order++;
        language[order-1] = new LanguagePreference("C", 148, order);
        order++;
        language[order-1] = new LanguagePreference("C++", 59, order);
        order++;
        language[order-1] = new LanguagePreference("C#", 58, order);
        order++;
        language[order-1] = new LanguagePreference("COBOL", 80, order);
        order++;
        language[order-1] = new LanguagePreference("FORTRAN", 90, order);
        order++;
        language[order-1] = new LanguagePreference("HTML", 43, order);
        order++;
        language[order-1] = new LanguagePreference("Java", 55, order);
        order++;
        language[order-1] = new LanguagePreference("Javascript", 54, order);
        order++;
        language[order-1] = new LanguagePreference("VBScript", 38, order);
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

        String selectedLanguage = null;
        if(isDefaultLanguageWindow){
            selectedLanguage = metricsSuite.getSelectedLanguage();
        } else {
            selectedLanguage = fpWindow.getFpData().getSelectedLanguage();
        }
        System.out.println(selectedLanguage);

        for (int i=0; i< language.length;i++){

            JCheckBox lCheckbox = new JCheckBox(language[i].getName());
            if(selectedLanguage!= null && selectedLanguage.equals(language[i].getName())){
                lCheckbox.setSelected(true);
            }
            group.add(lCheckbox);
            lChechboxArr.add(i,lCheckbox);
            languagePanel.add(lCheckbox);
        }

        doneButton = new JButton("Done");
        doneButton.addActionListener(this);
        languagePanel.add(doneButton);
        container.add(languagePanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == doneButton){

            if(isDefaultLanguageWindow){

                for(int i =0; i<lChechboxArr.size();i++){
                    if(lChechboxArr.get(i).isSelected()){

                        LanguagePreference preference = this.language[i];
                        metricsSuite.setSelectedLanguage(preference.getName());
                        metricsSuite.setLanguageCodeSize(preference.getAverageCodeSize());
                        dispose();
                        return;
                    }
                }
            } else {
                FunctionPointData fpData = fpWindow.getFpData();
                for(int i =0; i<lChechboxArr.size();i++){
                    if(lChechboxArr.get(i).isSelected()){

                        LanguagePreference preference = this.language[i];
                        fpData.setSelectedLanguage(preference.getName());
                        fpData.setLanguageCodeSize(preference.getAverageCodeSize());
                        fpWindow.current_lang_1_des_txt.setText(preference.getName());
                        fpWindow.setFpData(fpData);
                        dispose();
                        return;
                    }
                }
            }
        }
    }
}
