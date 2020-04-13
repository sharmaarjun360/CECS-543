package com.MetricsSuite.Windows;

import com.MetricsSuite.GlobalConstants.MetricsConstants;
import com.MetricsSuite.Models.ProjectStatistics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;
import java.awt.*;
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
        statistics.calculateStatistics();

        JPanel panel = new JPanel(false);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20,10,0,0));

        JLabel fileMeta = new JLabel("<html>File name: "+this.selectedFile.getName() +
                "<br>File length in bytes: "+statistics.getFileSize() +
                "<br>File white space: "+statistics.getWs() +
                "<br>File comment space in bytes: "+ statistics.getCommentSpace() +
                "<br>Comment percentage of file: "+ statistics.getCommentPercentage()+"%</html>");
        panel.add(fileMeta);

        JLabel label = new JLabel("Halstead metrics: ");
        panel.add(label);

        JLabel halsteadMetricsValue = new JLabel("<html>" +
                "Unique operators: "+statistics.getUniqueOperators() +
                "<br>Unique operands: "+statistics.getUniqueOperands() +
                "<br>Total operators: "+statistics.getTotalOperators() +
                "<br>Total operands: "+statistics.getTotalOperands() +
                "<br>Program length (N) = " + statistics.getProgramLength() +
                "<br>Program vocabulary (n) = " + statistics.getProgramVocabulary() +
                "<br>Volumn = " + statistics.getVolumn() +
                "<br>Difficulty = " + statistics.getDifficulty() +
                "<br>Effort = " + statistics.getEffort() +
                "<br>Bugs expected = " + statistics.getBugs() +
                "</html>"
        );
        halsteadMetricsValue.setBorder(new EmptyBorder(0,10,30,0));
        panel.add(halsteadMetricsValue);

        label = new JLabel("McCabe's Cyclomatic Complexity:");
        panel.add(label);

        JLabel mcCabeValue = new JLabel("<html>" + statistics.getMccabeValues() + "</html>");
        mcCabeValue.setBorder(new EmptyBorder(0,10,0,0));
        panel.add(mcCabeValue);

        return panel;
    }
}
