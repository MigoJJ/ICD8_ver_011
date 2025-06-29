// File: src/main/java/com/migojj/icd8/MainFrame.java
package com.migojj.icd8;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import com.migojj.icd8.core.ExcelProcessor;
import com.migojj.icd8.core.CsvWriter;
import com.migojj.icd8.model.Icd8Entry;
import java.util.List;

public class MainFrame extends JFrame {
    private final JButton btnOpen = new JButton("Open Excel…");
    private final JButton btnConvert = new JButton("Convert to CSV");
    private final JLabel lblFile = new JLabel("No file selected");
    private File selectedFile;

    public MainFrame() {
        super("ICD-8 Excel→CSV Converter");
        initUI();
    }

    private void initUI() {
        btnConvert.setEnabled(false);

        btnOpen.addActionListener(e -> onOpen());
        btnConvert.addActionListener(e -> onConvert());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttons.add(btnOpen);
        buttons.add(btnConvert);

        setLayout(new BorderLayout(10, 10));
        add(lblFile, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);
    }

    private void onOpen() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            lblFile.setText(selectedFile.getName());
            btnConvert.setEnabled(true);
        }
    }

    private void onConvert() {
        if (selectedFile == null) return;

        try {
            ExcelProcessor processor = new ExcelProcessor();
            List<Icd8Entry> entries = processor.readExcel(selectedFile);

            File out = new File(
                selectedFile.getParentFile(),
                selectedFile.getName().replaceAll("\\.xlsx?$", ".csv")
            );
            CsvWriter writer = new CsvWriter();
            writer.writeCsv(entries, out);

            JOptionPane.showMessageDialog(
                this,
                "Conversion complete:\n" + out.getAbsolutePath(),
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this,
                "Error: " + ex.getMessage(),
                "Conversion Failed",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
