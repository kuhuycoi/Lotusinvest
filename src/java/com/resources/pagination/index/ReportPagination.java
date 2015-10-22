package com.resources.pagination.index;

import java.util.Calendar;

public class ReportPagination extends DefaultIndexPagination {

    private String grandController;
    private Integer awardType;
    private final static String CHANGE_MONTH = "/ChangeMonth";
    private final static String CHANGE_YEAR = "/ChangeYear";
    private Integer month;
    private Integer year;

    public ReportPagination(String viewTitle, String grandController, String viewName, Integer awardType) {
        setViewTitle(viewTitle);
        setOrderColmn("dateCreated");
        setAsc(false);
        setViewName(viewName);
        setChildrenController("/Report");
        this.grandController = grandController;
        this.awardType = awardType;
        Calendar c = Calendar.getInstance();
        month = c.get(Calendar.MONTH) + 1;
        year = c.get(Calendar.YEAR);
    }

    public String getCHANGE_MONTH() {
        return CHANGE_MONTH;
    }

    public String getCHANGE_YEAR() {
        return CHANGE_YEAR;
    }

    public String getGrandController() {
        return grandController;
    }

    public void setGrandController(String grandController) {
        this.grandController = grandController;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }
    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
