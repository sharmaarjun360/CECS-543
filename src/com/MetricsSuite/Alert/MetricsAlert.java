package com.MetricsSuite.Alert;

import javax.swing.*;
import java.awt.*;

public class MetricsAlert {

    private static MetricsAlert metricsAlert_instance = null;

    public static MetricsAlert getInstance() {
        if (metricsAlert_instance == null)
            metricsAlert_instance = new MetricsAlert();
        return metricsAlert_instance;
    }
    private MetricsAlert() {

    }
    public void showAlert(Component context, String message){
        JOptionPane.showMessageDialog(context,message);
    }



    public int showConfirmAlert(Component context, String message){
        int input = JOptionPane.showConfirmDialog(context, message);
        return input;
    }

}
