// File: src/main/java/com/migojj/icd8/MainApplication.java
package com.migojj.icd8;

import javax.swing.SwingUtilities;

import com.migojj.icd8.ui.MainFrame;

public class MainApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
