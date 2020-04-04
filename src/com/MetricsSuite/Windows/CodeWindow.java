package com.MetricsSuite.Windows;

import com.MetricsSuite.Models.ProjectStatistics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;
import java.io.File;

public class CodeWindow extends JFrame {

    TreePath file;
    File selectedFile;

    public CodeWindow(TreePath file){
        this.file = file;
    }

    public CodeWindow(File file){
        this.selectedFile = file;
        this.selectedFile = file;
    }

    public JComponent createNewDataPanel ()
    {
        ProjectStatistics statistics = new ProjectStatistics(this.selectedFile);
        String mccabeValues = statistics.calculateStatistics();

        JPanel panel = new JPanel(false);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20,10,0,0));

        JLabel label = new JLabel("File name: "+this.selectedFile.getName());
        panel.add(label);

        label = new JLabel("File length in bytes: "+this.selectedFile.length());
        panel.add(label);

        label = new JLabel("File white space: ");
        panel.add(label);

        label = new JLabel("File comment space in bytes: ");
        panel.add(label);

        label = new JLabel("Comment percentage of file: ");
        panel.add(label);

        JLabel mccabeLabel = new JLabel("McCabe's Cyclomatic Complexity:");
        JLabel mccabeValuesLabel = new JLabel("<html>"+mccabeValues+"</html>");

        mccabeValuesLabel.setHorizontalAlignment(JLabel.LEFT);
        mccabeValuesLabel.setBorder(new EmptyBorder(0,10,0,0));
        panel.add(mccabeLabel);
        panel.add(mccabeValuesLabel);

        return panel;
    }
}
