package com.resources.pagination.admin;


public class AdminPagination extends DefaultAdminPagination {

    private String grandController;
    
    public AdminPagination(String viewName, String grandController) {
        setViewTitle("Danh sách quản trị viên");
        setOrderColmn("id");
        setChildrenController("/Permission");
        setInsertViewName("/permission_admin_insert_modal");
        setEditViewName("/permission_admin_edit_modal");
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
