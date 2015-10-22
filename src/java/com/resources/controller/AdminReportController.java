package com.resources.controller;

import com.resources.facade.HistoryAwardFacade;
import com.resources.bean.ExcelFile;
import com.resources.pagination.admin.DefaultAdminPagination;
import com.resources.pagination.admin.ReportPagination;
import com.resources.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/Admin/Report")
public class AdminReportController {

    @Autowired
    private HttpSession session;
    private final HistoryAwardFacade hFacade;

    public AdminReportController() {
        hFacade = new HistoryAwardFacade();
    }

    @RequestMapping(value = "/ComissionDistributor", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getDefaultComissionDistributorView() {
        ReportPagination reportPagination = new ReportPagination("Thống kê hoa hồng nhà phân phối", "cusId", true, "/ComissionDistributor", "/report_commission_distributor");
        session.setAttribute("COMISSION_DISTRIBUTOR_PAGINATION", reportPagination);
        return new ModelAndView(DefaultAdminPagination.CONTAINER_FOLDER + reportPagination.getViewName());
    }

    @RequestMapping(value = "/ComissionDistributor/DisplayPerPage/{displayPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeDisplayPerPageForComissionDistributorView(@PathVariable("displayPerPage") int displayPerPage) {
        ReportPagination reportPagination = (ReportPagination) session.getAttribute("COMISSION_DISTRIBUTOR_PAGINATION");
        if (reportPagination != null) {
            reportPagination.setDisplayPerPage(displayPerPage);
        }
        return comissionDistributorView(reportPagination);
    }

    @RequestMapping(value = "/ComissionDistributor/OrderData/{orderBy}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView orderByComissionDistributorView(@PathVariable("orderBy") String orderBy) {
        ReportPagination reportPagination = (ReportPagination) session.getAttribute("COMISSION_DISTRIBUTOR_PAGINATION");
        if (reportPagination != null) {
            if (reportPagination.getOrderColmn().equals(orderBy)) {
                reportPagination.setAsc(!reportPagination.isAsc());
            }
            reportPagination.setOrderColmn(orderBy);
        }
        return comissionDistributorView(reportPagination);
    }

    @RequestMapping(value = "/ComissionDistributor/GoTo/{page}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView gotoComissionDistributorView(@PathVariable("page") int page) {
        ReportPagination reportPagination = (ReportPagination) session.getAttribute("COMISSION_DISTRIBUTOR_PAGINATION");
        if (reportPagination != null) {
            reportPagination.setCurrentPage(page);
        }
        return comissionDistributorView(reportPagination);
    }

    @RequestMapping(value = "/ComissionDistributor/ChangeMonth/{month}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeMonthComissionDistributorView(@PathVariable("month") int month) {
        ReportPagination reportPagination = new ReportPagination("Thống kê hoa hồng nhà phân phối", "cusId", true, "/ComissionDistributor", "/report_commission_distributor");
        reportPagination.setMonth(month);
        return comissionDistributorView(reportPagination);
    }

    @RequestMapping(value = "/ComissionDistributor/ChangeYear/{year}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeYearComissionDistributorView(@PathVariable("year") int year) {
        ReportPagination reportPagination = new ReportPagination("Thống kê hoa hồng nhà phân phối", "cusId", true, "/ComissionDistributor", "/report_commission_distributor");
        reportPagination.setYear(year);
        return comissionDistributorView(reportPagination);
    }

    @RequestMapping(value = "/ComissionDistributor/Search", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView searchComissionDistributorView(@RequestBody Map map) {
        ReportPagination reportPagination = (ReportPagination) session.getAttribute("COMISSION_DISTRIBUTOR_PAGINATION");
        if (reportPagination == null) {
            reportPagination = new ReportPagination("Thống kê hoa hồng nhà phân phối", "cusId", true, "/ComissionDistributor", "/report_commission_distributor");
        }
        String searchString = (String) map.get("searchString");
        if (StringUtils.isEmpty(searchString)) {
            return comissionDistributorView(null);
        }
        List<String> keywords = (List) map.get("keywords");
        reportPagination.setSearchString(searchString);
        reportPagination.setKeywords(keywords);
        return comissionDistributorView(reportPagination);
    }

    private ModelAndView comissionDistributorView(ReportPagination reportPagination) {
        if (reportPagination == null) {
            reportPagination = new ReportPagination("Thống kê hoa hồng nhà phân phối", "cusId", true, "/ComissionDistributor", "/report_commission_distributor");
        }
        hFacade.pageData(reportPagination);
        session.setAttribute("COMISSION_DISTRIBUTOR_PAGINATION", reportPagination);
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + reportPagination.getViewName());
    }

    @RequestMapping(value = "/ComissionDistributor/Export", method = RequestMethod.GET)
    public ModelAndView exportView() {
        ReportPagination reportPagination = (ReportPagination) session.getAttribute("COMISSION_DISTRIBUTOR_PAGINATION");
        if (reportPagination == null) {
            reportPagination = new ReportPagination("Thống kê hoa hồng nhà phân phối", "cusId", true, "/ComissionDistributor", "/report_commission_distributor");
        }
        ExcelFile file = new ExcelFile();
        hFacade.setExportFile(file, reportPagination.getMonth(), reportPagination.getYear());
        return new ModelAndView("ExcelView", "myModel", file);
    }

}
