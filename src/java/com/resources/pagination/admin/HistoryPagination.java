package com.resources.pagination.admin;

public class HistoryPagination extends DefaultAdminPagination {

    private String grandController;

    public HistoryPagination(String viewTitle, String grandController, String viewName) {
        setViewTitle(viewTitle);
        setOrderColmn("dateCreated");
        setAsc(false);
        setViewName(viewName);
        setChildrenController("/History");
        this.grandController = grandController;
        setInsertViewName("/history_deposit_pv_modal");
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

    public HistoryPagination(String viewTitle, String orderColumn, boolean asc, String grandController, String viewName, String insertViewName) {
        setViewTitle(viewTitle);
        setOrderColmn(orderColumn);
        setAsc(asc);
        setViewName(viewName);
        setChildrenController("/History");
        this.grandController = grandController;
        setInsertViewName(insertViewName);
    }

    public String getGrandController() {
        return grandController;
    }

    public void setGrandController(String grandController) {
        this.grandController = grandController;
    }
}
