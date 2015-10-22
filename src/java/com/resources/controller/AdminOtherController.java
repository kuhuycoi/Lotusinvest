package com.resources.controller;

import com.resources.facade.SystemConfigFacade;
import com.resources.entity.SystemConfig;
import com.resources.pagination.admin.DefaultAdminPagination;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/Admin/Other")
public class AdminOtherController {

    private final SystemConfigFacade sFacade;
    private final SystemConfig systemConfig;

    public AdminOtherController() {
        sFacade = new SystemConfigFacade();
        systemConfig = (SystemConfig) sFacade.find(1);
    }

    @RequestMapping(value = "/SystemConfig", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getSystemConfigView(ModelMap mm) {
        mm.put("SYSTEM_CONFIG", systemConfig);
        return new ModelAndView(DefaultAdminPagination.CONTAINER_FOLDER + "/other_system_config");
    }
}
