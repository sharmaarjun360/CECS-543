package com.MetricsSuite.ActionListeners;

import com.MetricsSuite.*;
import com.MetricsSuite.Alert.MetricsAlert;
import com.MetricsSuite.FileChooser.SingleRootFileSystemView;
import com.MetricsSuite.Models.ProjectData;
import com.MetricsSuite.Windows.FunctionPointWindowMyVersion;
import com.MetricsSuite.Windows.LanguageWindow;
import com.MetricsSuite.Windows.MainWindow;
import com.MetricsSuite.Windows.NewProjectWindow;
import com.MetricsSuite.Error.MetricsError;
import com.MetricsSuite.GlobalConstants.MetricsConstants;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ActionListener_MainWindow implements ActionListener {

    private static ActionListener_MainWindow actionListener_MainWindow_Instance = null;
    private Component context;
    private static JFrame activeSubWindow = null;

    private ActionListener_MainWindow(Component context) {
        this.context = context;
    }

    /**
     * Returns singleton instance
     *
     * @param context
     * @return
     */
    public static ActionListener_MainWindow getInstance(Component context) {
        if (actionListener_MainWindow_Instance == null)
            actionListener_MainWindow_Instance = new ActionListener_MainWindow(context);
        return actionListener_MainWindow_Instance;
    }

    /**
     * Action performed on main window are managed here
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case MetricsConstants.P_MENU_ITEM_FILE_NEW:
                removeAllTabbedPane();
                activeSubWindow = newProjectWindow(context);
                break;
            case MetricsConstants.P_MENU_ITEM_FILE_OPEN:
                openProject(context);
                break;
            case MetricsConstants.P_MENU_ITEM_FILE_EXIT:
                exitTheApplication();
                break;
            case MetricsConstants.P_MENU_ITEM_FILE_SAVE:
                try {
                    saveProject(MetricsSuite.getInstance().getProjectData());
                } catch (IOException ioError) {
                    ioError.printStackTrace();
                } catch (MetricsError metricsError) {
                    MetricsAlert.getInstance().showAlert(context, metricsError.toString());
                }
                break;
            case MetricsConstants.P_MENU_ITEM_PREFERENCES_LANGUAGE:
                openLanguageWindow();
                break;
            case MetricsConstants.P_MENU_ITEM_METRICS_ENTER_FP_DATA:
                // TODO: 21/02/20 Use call backs to communicate between View and controller
                // getMainTabbedPane incorrect approch use callbacks instead
                newFunctionPointPane((JFrame) context, ((MainWindow) context).mainTabbedPane);
                break;
            default:
        }
    }

    /**
     * Saves Project data to location defined in Metrics Constant
     *
     * @param projectData
     * @return true after successful transaction
     * @throws MetricsError
     * @throws IOException
     */
    private boolean saveProject(ProjectData projectData) throws MetricsError, IOException {
        if (projectData == null) {
            throw new MetricsError(MetricsError.ERROR_CODE.ERROR_NULL_PROJECT);
        }
        String projectName = projectData.getProjectName();
        File projectDir = new File(MetricsConstants.PROJECT_DATA_PATH);
        if (!projectDir.exists()) {
            projectDir.mkdir();
        }
        File projectFile = new File(projectDir.getPath() + "/" + projectName + MetricsConstants.PROJECT_EXTENSION);
        FileOutputStream outFile;
        outFile = new FileOutputStream(projectFile);
        ObjectOutputStream outObject = new ObjectOutputStream(outFile);
        outObject.writeObject(projectData);
        outObject.close();
        return true;
    }

    /**
     * Opens JFile chooser, by default opens to the location defined in Metrics constant
     *
     * @param context
     * @return
     */
    private boolean openProject(Component context) {
        File root = new File(MetricsConstants.PROJECT_DATA_PATH);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(MetricsConstants.PROJECT_NAME, MetricsConstants.PROJECT_FILE_TYPE);
        FileSystemView fsv = new SingleRootFileSystemView(root);
        JFileChooser fileChooser = new JFileChooser(fsv);
        fileChooser.setFileFilter(filter);
        String projectFilePath = "";
        int fileChooserOption = fileChooser.showOpenDialog(context);
        ProjectData p1 = null;
        if (fileChooserOption == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            projectFilePath = selectedFile.getAbsolutePath();
            System.out.println("Selected file: " + projectFilePath);
            p1 = (ProjectData) readProjectDataFromFile(projectFilePath, MetricsSuite.getInstance().getProjectData());
        }
        return true;
    }

    /**
     * Reads the file at path selected
     *
     * @param filepath
     * @param objectType
     * @return
     */
    private Object readProjectDataFromFile(String filepath, Object objectType) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            objectType = objectIn.readObject();
            objectIn.close();
            return objectType;
        } catch (Exception ex) {
            MetricsAlert.getInstance().showAlert(context, new MetricsError(MetricsError.ERROR_CODE.ERROR_READING_PROJECT).toString());
//            ex.printStackTrace();
            return null;
        }
    }

    private JFrame openLanguageWindow() {
        if (activeSubWindow != null) {
            activeSubWindow.dispose();
            activeSubWindow = null;
        }
        LanguageWindow languageWindow = new LanguageWindow();
        languageWindow.setVisible(true);
        activeSubWindow = languageWindow;
        return languageWindow;
    }

    private void removeAllTabbedPane(){
        ((MainWindow)context).mainTabbedPane.removeAll();
    }

    private JFrame newProjectWindow(Component context) {
        NewProjectWindow newProject = null;
        if (context instanceof MainWindow) {
            if (activeSubWindow != null) {
                activeSubWindow.dispose();
                activeSubWindow = null;
            }
            newProject = new NewProjectWindow((MainWindow) context);
            newProject.setVisible(true);
        }
        return newProject;
    }

    private void newFunctionPointPane(JFrame parentFrame, JTabbedPane mainTabbedPane) {

        addEmptyFunctionPointTabToMainPane(mainTabbedPane);
//        mainPane.setSize(context.getWidth(),context.getHeight());
        parentFrame.revalidate();
//        parentFrame.pack();
//        mainPane.setBounds(200,50,200,200);
    }

    private JComponent addEmptyFunctionPointTabToMainPane(JTabbedPane mainPane) {
        ImageIcon icon = createImageIcon(MetricsConstants.PROJECT_IMAGES + MetricsConstants.PROJECT_IMAGE_SUN);
        JComponent panel = createNewFunctionPointPanel("We have to add custom panel here "+"@Method: addEmptyFunctionPointTabToMainPane \n @ class: ActionListener_MainWindow ");

        mainPane.addTab(MetricsConstants.P_TAB_TITLE, null, panel, "Some tool tip");
        return panel;
    }

    /**
     * This Function creates Empty FunctionPoint Panel
     * @param text
     * @return
     */
    protected JComponent createNewFunctionPointPanel(String text) {
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
        JButton compute_fp_btn = new JButton("Compute FP");
        JButton val_adjust_btn = new JButton("Value Adujstment");
        JButton compute_code_size_btn = new JButton("Compute Code Size");
        JButton change_lang_btn = new JButton("Change Language");

        //objects of radio button
        JRadioButton ext_ip_r_s = new JRadioButton("3");
        JRadioButton ext_ip_r_a = new JRadioButton("4");
        JRadioButton ext_ip_r_c = new JRadioButton("6");
        ButtonGroup ext_ip_r = new ButtonGroup();
        ext_ip_r.add(ext_ip_r_s);
        ext_ip_r.add(ext_ip_r_a);
        ext_ip_r.add(ext_ip_r_c);

        JRadioButton ext_op_r_s = new JRadioButton("4");
        JRadioButton ext_op_r_a = new JRadioButton("5");
        JRadioButton ext_op_r_c = new JRadioButton("7");
        ButtonGroup ext_op_r = new ButtonGroup();
        ext_op_r.add(ext_op_r_s);
        ext_op_r.add(ext_op_r_a);
        ext_op_r.add(ext_op_r_c);

        JRadioButton ext_inq_r_s = new JRadioButton("3");
        JRadioButton ext_inq_r_a = new JRadioButton("4");
        JRadioButton ext_inq_r_c = new JRadioButton("6");
        ButtonGroup ext_inq_r = new ButtonGroup();
        ext_inq_r.add(ext_inq_r_s);
        ext_inq_r.add(ext_inq_r_a);
        ext_inq_r.add(ext_inq_r_c);

        JRadioButton ext_lf_r_s = new JRadioButton("7");
        JRadioButton ext_lf_r_a = new JRadioButton("10");
        JRadioButton ext_lf_r_c = new JRadioButton("15");
        ButtonGroup ext_lf_r = new ButtonGroup();
        ext_lf_r.add(ext_lf_r_s);
        ext_lf_r.add(ext_lf_r_a);
        ext_lf_r.add(ext_lf_r_c);

        JRadioButton ext_if_r_s = new JRadioButton("5");
        JRadioButton ext_if_r_a = new JRadioButton("7");
        JRadioButton ext_if_r_c = new JRadioButton("10");
        ButtonGroup ext_if_r = new ButtonGroup();
        ext_if_r.add(ext_if_r_s);
        ext_if_r.add(ext_if_r_a);
        ext_if_r.add(ext_if_r_c);


        //objects of text boxes
        JTextField ext_ip_txt = new JTextField();
        JTextField ext_op_txt = new JTextField();
        JTextField ext_inq_txt = new JTextField();
        JTextField int_lf_txt = new JTextField();
        JTextField ext_if_txt = new JTextField();
        JTextField ext_ip_des_txt = new JTextField();
        JTextField ext_op_des_txt = new JTextField();
        JTextField ext_inq_des_txt = new JTextField();
        JTextField int_lf_des_txt = new JTextField();
        JTextField ext_if_des_txt = new JTextField();
        JTextField total_count_des_txt = new JTextField();
        JTextField compute_fp_des_txt = new JTextField();
        JTextField val_adj_des_txt = new JTextField("0");
        JTextField current_lang_1_des_txt = new JTextField("Java");
        JTextField current_lang_2_des_txt = new JTextField();

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

        panel.setLayout(null);
        return panel;
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MetricsSuite.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private void exitTheApplication() {
        System.exit(0);
    }
}
