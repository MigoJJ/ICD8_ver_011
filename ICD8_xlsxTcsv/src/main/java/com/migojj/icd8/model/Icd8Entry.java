package com.migojj.icd8.model;

public class Icd8Entry {
    private final String code;
    private final String koreanDescription;
    private final String englishDescription;

    public Icd8Entry(String code, String koreanDescription, String englishDescription) {
        this.code = code;
        this.koreanDescription = koreanDescription;
        this.englishDescription = englishDescription;
    }

    public String getCode() {
        return code;
    }

    public String getKoreanDescription() {
        return koreanDescription;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }
}
