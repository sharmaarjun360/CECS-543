package com.MetricsSuite.Windows;

import com.MetricsSuite.Models.ProjectStatistics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
        JPanel secondaryPanel = new JPanel(false);
        secondaryPanel.setLayout(new BoxLayout(secondaryPanel,BoxLayout.Y_AXIS));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
       // panel.setBorder(new EmptyBorder(20,10,0,0));


        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        JLabel fileMeta = new JLabel("<html>File name: "+this.selectedFile.getName() +
                "<br>File length in bytes: "+statistics.getFileSize() +
                "<br>File white space: "+statistics.getWs() +
                "<br>File comment space in bytes: "+ statistics.getCommentSpace() +
                "<br>Comment percentage of file: "+ df.format(statistics.getCommentPercentage())+"%</html>");
        secondaryPanel.add(fileMeta);

        JLabel label = new JLabel("Halstead metrics: ");
        secondaryPanel.add(label);

        JLabel halsteadMetricsValue = new JLabel("<html>" +
                "Unique operators: "+statistics.getUniqueOperators() +
                "<br>Unique operands: "+statistics.getUniqueOperands() +
                "<br>Total operators: "+statistics.getTotalOperators() +
                "<br>Total operands: "+statistics.getTotalOperands() +
                "<br>Program length (N) = " + statistics.getProgramLength() +
                "<br>Program vocabulary (n) = " + statistics.getProgramVocabulary() +
                "<br>Volumn = " + df.format(statistics.getVolumn()) +
                "<br>Difficulty = " + df.format(statistics.getDifficulty()) +
                "<br>Effort = " + df.format(statistics.getEffort()) +
                "  Time = " + df.format(statistics.getTime()) +
                " (" + df.format(statistics.getTime()/60) +" minutes or " + df.format(statistics.getTime()/3600) + " hours or ? person months" + ")" +
                "<br>Bugs expected = " + df.format(statistics.getBugs()) +
                "</html>"
        );
        halsteadMetricsValue.setBorder(new EmptyBorder(0,10,30,0));
        secondaryPanel.add(halsteadMetricsValue);

        label = new JLabel("McCabe's Cyclomatic Complexity:");
        secondaryPanel.add(label);

        JLabel mcCabeValue = new JLabel("<html>" + statistics.getMccabeValues() + "</html>");
        mcCabeValue.setBorder(new EmptyBorder(0,10,0,0));
        secondaryPanel.add(mcCabeValue);
        JScrollPane scrollPane = new JScrollPane(secondaryPanel);
        panel.add(scrollPane);

        return panel;
    }
}
