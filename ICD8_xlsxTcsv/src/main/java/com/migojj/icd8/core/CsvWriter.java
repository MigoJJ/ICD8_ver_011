package com.migojj.icd8.core;

import com.migojj.icd8.model.Icd8Entry;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CsvWriter {

    /**
     * Writes out a CSV file of ICD-8 entries, skipping any duplicate codes.
     *
     * @param entries     the list of entries to write
     * @param outputFile  the target CSV file
     * @throws IOException if an I/O error occurs
     */
    public void writeCsv(List<Icd8Entry> entries, File outputFile) throws IOException {
        Set<String> seenCodes = new HashSet<>();

        try (BufferedWriter writer = new BufferedWriter(
                 new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8)))
        {
            // Write header
            writer.write("code,koreanDescription,englishDescription");
            writer.newLine();

            for (Icd8Entry entry : entries) {
                String code = entry.getCode();
                // skip duplicates
                if (!seenCodes.add(code)) {
                    continue;
                }

                writer.write(escapeCsv(code));
                writer.write(",");
                writer.write(escapeCsv(entry.getKoreanDescription()));
                writer.write(",");
                writer.write(escapeCsv(entry.getEnglishDescription()));
                writer.newLine();
            }
        }
    }

    /**
     * Escapes a field for CSV:
     *  - doubles any existing quotes
     *  - wraps the field in quotes if it contains commas, quotes, or line breaks
     */
    private String escapeCsv(String field) {
        if (field == null) {
            return "";
        }
        String escaped = field.replace("\"", "\"\"");
        boolean needQuotes = escaped.contains(",") 
                          || escaped.contains("\"")
                          || escaped.contains("\n") 
                          || escaped.contains("\r");
        return needQuotes ? "\"" + escaped + "\"" : escaped;
    }
}
