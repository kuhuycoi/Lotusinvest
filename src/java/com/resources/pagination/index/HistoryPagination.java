package com.resources.pagination.index;

public class HistoryPagination extends DefaultIndexPagination {

    private String grandController;
    private Integer awardType;

    public HistoryPagination(String viewTitle, String grandController, String viewName) {
        setViewTitle(viewTitle);
        setOrderColmn("dateCreated");
        setAsc(false);
        setViewName(viewName);
        setChildrenController("/History");
        this.grandController = grandController;
        setInsertViewName("/history_deposit_pv");
    }

    public HistoryPagination(String viewTitle, String orderColumn, boolean asc, String grandController, String viewName) {
        setViewTitle(viewTitle);
        setOrderColmn(orderColumn);
        setAsc(asc);
        setViewName(viewName);
        setChildrenController("/History");
        this.grandController = grandController;
        setInsertViewName("/history_deposit_pv_modal");
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
}
