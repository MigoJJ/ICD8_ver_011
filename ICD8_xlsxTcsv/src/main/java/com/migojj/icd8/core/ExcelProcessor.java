package com.migojj.icd8.core;

import com.migojj.icd8.model.Icd8Entry;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelProcessor {

    /**
     * Reads the first sheet of the given Excel file and maps each row
     * (after the header) to an Icd8Entry.
     *
     * Expects columns: code | koreanDescription | englishDescription
     */
    public List<Icd8Entry> readExcel(File excelFile) throws IOException {
        List<Icd8Entry> entries = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            boolean firstRow = true;
            for (Row row : sheet) {
                if (firstRow) {
                    firstRow = false;  // skip header
                    continue;
                }
                String code = getCellStringValue(row.getCell(0));
                String kr   = getCellStringValue(row.getCell(1));
                String en   = getCellStringValue(row.getCell(2));

                // only add if code is non-empty
                if (!code.isBlank()) {
                    entries.add(new Icd8Entry(code, kr, en));
                }
            }
        }

        return entries;
    }

    /** Safely converts any cell to its String representation */
    private String getCellStringValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        return switch (cell.getCellType()) {
            case STRING  -> cell.getStringCellValue().trim();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield cell.getDateCellValue().toString();
                } else {
                    yield Double.toString(cell.getNumericCellValue());
                }
            }
            case BOOLEAN -> Boolean.toString(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default       -> "";
        };
    }
}
