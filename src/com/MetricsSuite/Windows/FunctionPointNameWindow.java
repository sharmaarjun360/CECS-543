package com.MetricsSuite.Windows;

import com.MetricsSuite.Alert.MetricsAlert;
import com.MetricsSuite.GlobalConstants.MetricsConstants;
import com.MetricsSuite.MetricsSuite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionPointNameWindow extends JFrame {

    Container container = getContentPane();
    public MainWindow mainWindow;
    public JLabel mainLabel, label;
    public JTextField fpWindowName_text;
    public JButton ok, cancel;

    public FunctionPointNameWindow(MainWindow parentFrame){

        this.mainWindow = parentFrame;
        container.setLayout(null);
        initComponent();
        addActionEvent();
        setSize(MetricsConstants.FUNCTION_POINT_NAME_WINDOW_WIDTH,MetricsConstants.FUNCTION_POINT_NAME_WINDOW_HEIGHT);
        setLocation(MetricsConstants.NEW_PROJECT__WINDOW_LOCATION_X,MetricsConstants.NEW_PROJECT__WINDOW_LOCATION_Y);
        setTitle("Function Point Window Name");

    }

    public void initComponent() {

//        mainLabel = new JLabel("");
//        mainLabel.setBounds(0, 20, 400, 20);
//        mainLabel.setHorizontalAlignment(JLabel.CENTER);
//        container.add(mainLabel);

        label = new JLabel("Enter Name:");
        label.setBounds(20, 30, 100, 20);
        container.add(label);
        fpWindowName_text = new JTextField();
        fpWindowName_text.setBounds(120, 30, 150, 20);
        container.add(fpWindowName_text);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBounds(10,60,200,40);
        container.add(buttonPanel);
        ok=new JButton("Ok");
        cancel=new JButton("Cancel");
        buttonPanel.add(ok);
        buttonPanel.add(cancel);

        container.add(buttonPanel);
    }

    public void addActionEvent() {

        FunctionPointNameWindow self = this;

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = self.fpWindowName_text.getText();

                if(name == null || name.length()==0){
                    // show prompt
                    MetricsAlert.getInstance().showAlert(self,"Please Enter Function Point Window Name");
                    self.fpWindowName_text.requestFocusInWindow();
                    return;
                }

                // create function point window
                FunctionPointWindow fp = new FunctionPointWindow(self.mainWindow,false, null, name);
                JComponent panel = fp.createNewFunctionPointPanel();
                self.mainWindow.mainTabbedPane.addTab(name, null, panel, name);
                self.mainWindow.mainTabbedPane.setSelectedIndex(self.mainWindow.mainTabbedPane.getTabCount() - 1);
                self.mainWindow.updateTree(MetricsSuite.getInstance().getProjectData());
                self.setVisible(false);
                self.dispose();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                self.setVisible(false);
                self.dispose();
            }
        });
    }

}
