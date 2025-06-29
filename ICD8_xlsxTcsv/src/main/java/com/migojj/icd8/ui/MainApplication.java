package com.migojj.icd8.ui;

import javax.swing.SwingUtilities;


public class MainApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
