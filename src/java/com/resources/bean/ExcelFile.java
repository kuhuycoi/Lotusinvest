package com.resources.bean;

import java.util.List;

public class ExcelFile {

    private String fileName;
    private List<String> titles;
    private List contents;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List getContents() {
        return contents;
    }

    public void setContents(List contents) {
        this.contents = contents;
    }

    public ExcelFile() {
    }

}
