package com.resources.controller;

import com.resources.facade.HistoryAwardFacade;
import com.resources.pagination.index.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/Report")
public class IndexReportController {

    @Autowired
    HttpSession session;
    private final HistoryAwardFacade hFacade;

    public IndexReportController() {
        hFacade = new HistoryAwardFacade();
    }

    //Award    
    @RequestMapping(value = "/Award/{type}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getDefaultAwardView(@PathVariable(value = "type") Integer type, ModelMap mm) {
        ReportPagination awardPagination = new ReportPagination("Lịch sử thưởng", "/Award", "/report_award", type);
        switch (type) {
            case 1: {
                awardPagination.setViewTitle("Lịch sử thưởng hoa hồng mã rơi");
                break;
            }
            case 4: {
                awardPagination.setViewTitle("Lịch sử thưởng hoa hồng trực tiếp");
                break;
            }
            case 13: {
                awardPagination.setViewTitle("Lịch sử thưởng hoa hồng cân cặp");
                break;
            }
            case 28: {
                awardPagination.setViewTitle("Lịch sử thưởng hoa hồng đầu tư");
                break;
            }
            case 29: {
                awardPagination.setViewTitle("Lịch sử thưởng hoa hồng lên câp");
                break;
            }
            default: {
                awardPagination.setViewTitle("Lịch sử thưởng");
                break;
            }
        }
        session.setAttribute("INDEX_HISTORY_AWARD_PAGINATION", awardPagination);
        return new ModelAndView(DefaultIndexPagination.CONTAINER_FOLDER + awardPagination.getViewName());
    }

    @RequestMapping(value = "/Award/DisplayPerPage/{displayPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeDisplayPerPageForAwardView(@PathVariable("displayPerPage") int displayPerPage, ModelMap mm) {
        ReportPagination awardPagination = (ReportPagination) session.getAttribute("INDEX_HISTORY_AWARD_PAGINATION");
        if (awardPagination != null) {
            awardPagination.setDisplayPerPage(displayPerPage);

        }
        return customerRankCustomerView(awardPagination);
    }

    @RequestMapping(value = "/Award/OrderData/{orderBy}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView orderByAwardView(@PathVariable("orderBy") String orderBy, ModelMap mm) {
        ReportPagination awardPagination = (ReportPagination) session.getAttribute("INDEX_HISTORY_AWARD_PAGINATION");
        if (awardPagination != null) {
            if (awardPagination.getOrderColmn().equals(orderBy)) {
                awardPagination.setAsc(!awardPagination.isAsc());
            }
            awardPagination.setOrderColmn(orderBy);
        }
        return customerRankCustomerView(awardPagination);
    }

    @RequestMapping(value = "/Award/GoTo/{page}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView gotoAwardView(@PathVariable("page") int page, ModelMap mm) {
        ReportPagination awardPagination = (ReportPagination) session.getAttribute("INDEX_HISTORY_AWARD_PAGINATION");
        if (awardPagination != null) {
            awardPagination.setCurrentPage(page);
        }
        return customerRankCustomerView(awardPagination);
    }

    @RequestMapping(value = "/Award/ChangeMonth/{month}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeMonthComissionDistributorView(@PathVariable("month") int month) {
        ReportPagination awardPagination = (ReportPagination) session.getAttribute("INDEX_HISTORY_AWARD_PAGINATION");
        if (awardPagination == null) {
            awardPagination = new ReportPagination("Lịch sử nạp PV", "/Award", "/report_award", null);
        }
        awardPagination.setMonth(month);
        return customerRankCustomerView(awardPagination);
    }

    @RequestMapping(value = "/Award/ChangeYear/{year}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeYearComissionDistributorView(@PathVariable("year") int year) {
        ReportPagination awardPagination = (ReportPagination) session.getAttribute("INDEX_HISTORY_AWARD_PAGINATION");
        if (awardPagination == null) {
            awardPagination = new ReportPagination("Lịch sử nạp PV", "/Award", "/report_award", null);
        }
        awardPagination.setYear(year);
        return customerRankCustomerView(awardPagination);
    }

    private ModelAndView customerRankCustomerView(ReportPagination awardPagination) {
        if (awardPagination == null) {
            awardPagination = new ReportPagination("Lịch sử nạp PV", "/Award", "/report_award", null);
        }
        hFacade.pageData(awardPagination, (Integer) session.getAttribute("CUSTOMER_ID"));
        session.setAttribute("INDEX_HISTORY_AWARD_PAGINATION", awardPagination);
        return new ModelAndView(DefaultIndexPagination.AJAX_FOLDER + awardPagination.getViewName());
    }
}
