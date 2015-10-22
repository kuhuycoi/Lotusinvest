package com.resources.pagination.index;


public class CustomerPagination extends DefaultIndexPagination {

    private String grandController;
    
    public CustomerPagination(String viewName, String grandController) {
        setViewTitle("Danh sách nhà phân phối");
        setOrderColmn("isActive");
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

    public String getGrandController() {
        return grandController;
    }

    public void setGrandController(String grandController) {
        this.grandController = grandController;
    }
}
