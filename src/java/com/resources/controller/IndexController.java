package com.resources.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class IndexController {
    
    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public ModelAndView getView(ModelMap mm) {
        return new ModelAndView("index");
    }
}
