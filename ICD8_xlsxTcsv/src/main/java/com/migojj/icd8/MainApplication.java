package com.migojj.icd8;

import javax.swing.SwingUtilities;
import com.migojj.icd8.ui.MainFrame;

public class MainApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}