package com.resources.pagination.admin;

import com.resources.entity.Module;

public class ModulePagination extends DefaultAdminPagination<Module> {

    private String grandController;

    public ModulePagination(String viewName, String grandController) {
        setViewTitle("Quản lý module");
        setOrderColmn("id");
        setViewName(viewName);
        setChildrenController("/Module");
        this.grandController = grandController;
    }

    public String getGrandController() {
        return grandController;
    }

    public void setGrandController(String grandController) {
        this.grandController = grandController;
    }

}
