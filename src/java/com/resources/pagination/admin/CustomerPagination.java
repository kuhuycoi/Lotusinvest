package com.resources.pagination.admin;


public class CustomerPagination extends DefaultAdminPagination {

    private String grandController;
    private final static String RESET_PASSWORD="/ResetPassword";
    
    public CustomerPagination(String viewName, String grandController) {
        setViewTitle("Danh sách nhà phân phối");
        setOrderColmn("id");
        setChildrenController("/Customer");
        setInsertViewName("/customer_insert_modal");
        setEditViewName("/customer_edit_modal");
        setViewName(viewName);
        this.grandController=grandController;
    }

    public CustomerPagination(String orderColumn,String viewName, String grandController) {
        setViewTitle("Danh sách nhà phân phối");
        setOrderColmn(orderColumn);
        setChildrenController("/Customer");
        setInsertViewName("/customer_insert_modal");
        setEditViewName("/customer_edit_modal");
        setViewName(viewName);
        this.grandController=grandController;
    }

    public String getRESET_PASSWORD() {
        return RESET_PASSWORD;
    }

    public String getGrandController() {
        return grandController;
    }

    public void setGrandController(String grandController) {
        this.grandController = grandController;
    }
    
}
