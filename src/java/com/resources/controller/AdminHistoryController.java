package com.resources.controller;

import com.resources.facade.CheckAwardsFacade;
import com.resources.facade.CustomerFacade;
import com.resources.facade.CustomerRankCustomerFacade;
import com.resources.facade.HistoryAwardFacade;
import com.resources.facade.PinSysFacade;
import com.resources.facade.RankCustomersFacade;
import com.resources.pagination.admin.HistoryPagination;
import com.resources.pagination.admin.DefaultAdminPagination;
import com.resources.pagination.admin.MessagePagination;
import com.resources.utils.StringUtils;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/Admin/History")
public class AdminHistoryController {

    @Autowired
    HttpSession session;
    private final CustomerRankCustomerFacade cRFacade;
    private final CustomerFacade cFacade;
    private final HistoryAwardFacade aFacade;
    private final RankCustomersFacade rCFacade;
    private final CheckAwardsFacade cAFacade;
    private final PinSysFacade pFacade;

    public AdminHistoryController() {
        cRFacade = new CustomerRankCustomerFacade();
        cFacade = new CustomerFacade();
        aFacade = new HistoryAwardFacade();
        rCFacade = new RankCustomersFacade();
        cAFacade = new CheckAwardsFacade();
        pFacade = new PinSysFacade();
    }

    //CustomerRankCustomer    
    @RequestMapping(value = "/CustomerRankCustomer", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getDefaultCustomerRankCustomerView(ModelMap mm) {
        HistoryPagination customerRankCustomerPagination = new HistoryPagination("Lịch sử nạp PV", "/CustomerRankCustomer", "/history_customer_rank_customer");
        session.setAttribute("CUSTOMER_RANK_CUSTOMER_PAGINATION", customerRankCustomerPagination);
        return new ModelAndView(DefaultAdminPagination.CONTAINER_FOLDER + customerRankCustomerPagination.getViewName());
    }

    @RequestMapping(value = "/CustomerRankCustomer/DisplayPerPage/{displayPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeDisplayPerPageForCustomerRankCustomerView(@PathVariable("displayPerPage") int displayPerPage, ModelMap mm) {
        HistoryPagination customerRankCustomerPagination = (HistoryPagination) session.getAttribute("CUSTOMER_RANK_CUSTOMER_PAGINATION");
        if (customerRankCustomerPagination != null) {
            customerRankCustomerPagination.setDisplayPerPage(displayPerPage);
        }
        return customerRankCustomerView(customerRankCustomerPagination);
    }

    @RequestMapping(value = "/CustomerRankCustomer/OrderData/{orderBy}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView orderByCustomerRankCustomerView(@PathVariable("orderBy") String orderBy, ModelMap mm) {
        HistoryPagination customerRankCustomerPagination = (HistoryPagination) session.getAttribute("CUSTOMER_RANK_CUSTOMER_PAGINATION");
        if (customerRankCustomerPagination != null) {
            if (customerRankCustomerPagination.getOrderColmn().equals(orderBy)) {
                customerRankCustomerPagination.setAsc(!customerRankCustomerPagination.isAsc());
            }
            customerRankCustomerPagination.setOrderColmn(orderBy);
        }
        return customerRankCustomerView(customerRankCustomerPagination);
    }

    @RequestMapping(value = "/CustomerRankCustomer/GoTo/{page}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView gotoCustomerRankCustomerView(@PathVariable("page") int page, ModelMap mm) {
        HistoryPagination customerRankCustomerPagination = (HistoryPagination) session.getAttribute("CUSTOMER_RANK_CUSTOMER_PAGINATION");
        if (customerRankCustomerPagination != null) {
            customerRankCustomerPagination.setCurrentPage(page);
        }
        return customerRankCustomerView(customerRankCustomerPagination);
    }

    @RequestMapping(value = "/CustomerRankCustomer/Search", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView searchDistributorView(@RequestBody Map map) {
        String searchString = (String) map.get("searchString");
        if (StringUtils.isEmpty(searchString)) {
            return customerRankCustomerView(null);
        }
        List<String> keywords = (List) map.get("keywords");
        HistoryPagination customerRankCustomerPagination = new HistoryPagination("Lịch sử nạp PV", "/CustomerRankCustomer", "/history_customer_rank_customer");
        customerRankCustomerPagination.setSearchString(searchString);
        customerRankCustomerPagination.setKeywords(keywords);
        return customerRankCustomerView(customerRankCustomerPagination);
    }

    private ModelAndView customerRankCustomerView(HistoryPagination customerRankCustomerPagination) {
        if (customerRankCustomerPagination == null) {
            customerRankCustomerPagination = new HistoryPagination("Lịch sử nạp PV", "/CustomerRankCustomer", "/history_customer_rank_customer");
        }
        cRFacade.pageData(customerRankCustomerPagination);
        session.setAttribute("CUSTOMER_RANK_CUSTOMER_PAGINATION", customerRankCustomerPagination);
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + customerRankCustomerPagination.getViewName());
    }

    @RequestMapping(value = "/CustomerRankCustomer/ViewInsert", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getCustomerRankCustomerViewInsert() {
        HistoryPagination customerRankCustomerPagination = (HistoryPagination) session.getAttribute("CUSTOMER_RANK_CUSTOMER_PAGINATION");
        if (customerRankCustomerPagination == null) {
            customerRankCustomerPagination = new HistoryPagination("Lịch sử nạp PV", "/CustomerRankCustomer", "/history_customer_rank_customer");
        }
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + customerRankCustomerPagination.getInsertViewName());
    }

    @RequestMapping(value = "/CustomerRankCustomer/ViewInsert/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getCustomerRankCustomerViewInsert(@PathVariable(value = "userName") String userName, ModelMap mm) {
        mm.put("USERNAME", userName);
        HistoryPagination customerRankCustomerPagination = (HistoryPagination) session.getAttribute("CUSTOMER_RANK_CUSTOMER_PAGINATION");
        if (customerRankCustomerPagination == null) {
            customerRankCustomerPagination = new HistoryPagination("Lịch sử nạp PV", "/CustomerRankCustomer", "/history_customer_rank_customer");
        }
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + customerRankCustomerPagination.getInsertViewName());
    }

    @RequestMapping(value = "/CustomerRankCustomer/Insert", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView insertCustomerRankCustomer(@RequestParam(value = "userName") String userName,
            @RequestParam(value = "rankCustomerId") Integer rankCustomerId,
            @RequestParam(value = "multipleGrateful", required = false) Integer multipleGrateful,
            ModelMap mm) {
        System.out.println(rankCustomerId + " - " + multipleGrateful + " - " + userName);
        ModelAndView mAV = new ModelAndView(DefaultAdminPagination.MESSAGE_FOLDER + MessagePagination.MESSAGE_VIEW);
        MessagePagination mP;
        Integer result;
        try {
            result = cRFacade.depositPv(userName, rankCustomerId, multipleGrateful);
        } catch (Exception e) {
            mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi! Vui lòng thử lại sau!");
            mm.put("MESSAGE_PAGINATION", mP);
            return mAV;
        }
        switch (result) {
            case 1: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Tài khoản đã từng nạp gói PV này!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            case 2: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_SUCCESS, "thành công", "Nạp PV thành công!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            case 3: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Yêu cầu nạp gói 250PV trước!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            default: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi! Vui lòng thử lại sau!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
        }
    }

    //Award  
    @RequestMapping(value = "/Award", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getDefaultAwardView() {
        HistoryPagination awardPagination = new HistoryPagination("Lịch sử thưởng", "/Award", "/history_award");
        session.setAttribute("HISTORY_AWARD_PAGINATION", awardPagination);
        return new ModelAndView(DefaultAdminPagination.CONTAINER_FOLDER + awardPagination.getViewName());
    }

    @RequestMapping(value = "/Award/DisplayPerPage/{displayPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeDisplayPerPageForAwardView(@PathVariable("displayPerPage") int displayPerPage) {
        HistoryPagination awardPagination = (HistoryPagination) session.getAttribute("HISTORY_AWARD_PAGINATION");
        if (awardPagination != null) {
            awardPagination.setDisplayPerPage(displayPerPage);
        }
        return awardView(awardPagination);
    }

    @RequestMapping(value = "/Award/OrderData/{orderBy}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView orderByAwardView(@PathVariable("orderBy") String orderBy, ModelMap mm) {
        HistoryPagination awardPagination = (HistoryPagination) session.getAttribute("HISTORY_AWARD_PAGINATION");
        if (awardPagination != null) {
            if (awardPagination.getOrderColmn().equals(orderBy)) {
                awardPagination.setAsc(!awardPagination.isAsc());
            }
            awardPagination.setOrderColmn(orderBy);
        }
        return awardView(awardPagination);
    }

    @RequestMapping(value = "/Award/GoTo/{page}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView gotoAwardView(@PathVariable("page") int page, ModelMap mm) {
        HistoryPagination awardPagination = (HistoryPagination) session.getAttribute("HISTORY_AWARD_PAGINATION");
        if (awardPagination != null) {
            awardPagination.setCurrentPage(page);
        }
        return awardView(awardPagination);
    }

    @RequestMapping(value = "/Award/Search", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView searchAwardView(@RequestBody Map map) {
        String searchString = (String) map.get("searchString");
        if (StringUtils.isEmpty(searchString)) {
            return awardView(null);
        }
        List<String> keywords = (List) map.get("keywords");
        HistoryPagination awardPagination = new HistoryPagination("Lịch sử thưởng", "/Award", "/history_award");
        awardPagination.setSearchString(searchString);
        awardPagination.setKeywords(keywords);
        return awardView(awardPagination);
    }

    private ModelAndView awardView(HistoryPagination awardPagination) {
        if (awardPagination == null) {
            awardPagination = new HistoryPagination("Lịch sử thưởng", "/Award", "/history_award");
        }
        aFacade.pageData(awardPagination);
        session.setAttribute("HISTORY_AWARD_PAGINATION", awardPagination);
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + awardPagination.getViewName());
    }

    @RequestMapping(value = "/Award/ViewInsert", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getAwardViewInsert() {
        HistoryPagination awardPagination = (HistoryPagination) session.getAttribute("HISTORY_AWARD_PAGINATION");
        if (awardPagination == null) {
            awardPagination = new HistoryPagination("Lịch sử thưởng", "/Award", "/history_award");
        }
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + awardPagination.getInsertViewName());
    }

    // Never up rank
    @RequestMapping(value = "/NeverUpRank", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getDefaultNeverUpRankView() {
        HistoryPagination neverUpRankPagination = new HistoryPagination("Danh sách chưa từng nạp PV", "id", true, "/NeverUpRank", "/history_never_up_rank");
        session.setAttribute("HISTORY_NEVER_UP_RANK_PAGINATION", neverUpRankPagination);
        return new ModelAndView(DefaultAdminPagination.CONTAINER_FOLDER + neverUpRankPagination.getViewName());
    }

    @RequestMapping(value = "/NeverUpRank/DisplayPerPage/{displayPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeDisplayPerPageForNeverUpRankView(@PathVariable("displayPerPage") int displayPerPage) {
        HistoryPagination neverUpRankPagination = (HistoryPagination) session.getAttribute("HISTORY_NEVER_UP_RANK_PAGINATION");
        if (neverUpRankPagination != null) {
            neverUpRankPagination.setDisplayPerPage(displayPerPage);

        }
        return neverUpRankView(neverUpRankPagination);
    }

    @RequestMapping(value = "/NeverUpRank/OrderData/{orderBy}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView orderByNeverUpRankView(@PathVariable("orderBy") String orderBy) {
        HistoryPagination neverUpRankPagination = (HistoryPagination) session.getAttribute("HISTORY_NEVER_UP_RANK_PAGINATION");
        if (neverUpRankPagination != null) {
            if (neverUpRankPagination.getOrderColmn().equals(orderBy)) {
                neverUpRankPagination.setAsc(!neverUpRankPagination.isAsc());
            }
            neverUpRankPagination.setOrderColmn(orderBy);
        }
        return neverUpRankView(neverUpRankPagination);
    }

    @RequestMapping(value = "/NeverUpRank/GoTo/{page}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView gotoNeverUpRankView(@PathVariable("page") int page) {
        HistoryPagination neverUpRankPagination = (HistoryPagination) session.getAttribute("HISTORY_NEVER_UP_RANK_PAGINATION");
        if (neverUpRankPagination != null) {
            neverUpRankPagination.setCurrentPage(page);
        }
        return neverUpRankView(neverUpRankPagination);
    }

    @RequestMapping(value = "/NeverUpRank/Search", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView searchNeverUpRankView(@RequestBody Map map) {
        String searchString = (String) map.get("searchString");
        if (StringUtils.isEmpty(searchString)) {
            return neverUpRankView(null);
        }
        List<String> keywords = (List) map.get("keywords");
        HistoryPagination neverUpRankPagination = new HistoryPagination("Danh sách chưa từng nạp PV", "id", true, "/NeverUpRank", "/history_never_up_rank");
        neverUpRankPagination.setSearchString(searchString);
        neverUpRankPagination.setKeywords(keywords);
        return neverUpRankView(neverUpRankPagination);
    }

    private ModelAndView neverUpRankView(HistoryPagination neverUpRankPagination) {
        if (neverUpRankPagination == null) {
            neverUpRankPagination = new HistoryPagination("Danh sách chưa từng nạp PV", "id", true, "/NeverUpRank", "/history_never_up_rank");
        }
        cFacade.pageDataNeverUpRank(neverUpRankPagination);
        session.setAttribute("HISTORY_NEVER_UP_RANK_PAGINATION", neverUpRankPagination);
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + neverUpRankPagination.getViewName());
    }

    @RequestMapping(value = "/NeverUpRank/ViewInsert/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getCustomerRankCustomerViewInsert1(@PathVariable(value = "userName") String userName, ModelMap mm) {
        mm.put("USERNAME", userName);
        HistoryPagination neverUpRankPagination = (HistoryPagination) session.getAttribute("CUSTOMER_RANK_CUSTOMER_PAGINATION");
        if (neverUpRankPagination == null) {
            neverUpRankPagination = new HistoryPagination("Danh sách chưa từng nạp PV", "id", true, "/NeverUpRank", "/history_never_up_rank");
        }
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + neverUpRankPagination.getInsertViewName());
    }

    @RequestMapping(value = "/NeverUpRank/ViewInsert", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getNeverUpRankViewInsert() {
        HistoryPagination neverUpRankPagination = (HistoryPagination) session.getAttribute("CUSTOMER_RANK_CUSTOMER_PAGINATION");
        if (neverUpRankPagination == null) {
            neverUpRankPagination = new HistoryPagination("Danh sách chưa từng nạp PV", "id", true, "/NeverUpRank", "/history_never_up_rank");
        }
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + neverUpRankPagination.getInsertViewName());
    }

    // Rank Customer
    @RequestMapping(value = "/RankCustomer", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getDefaultRankCustomerView(ModelMap mm) {
        HistoryPagination rankCustomerPagniation = new HistoryPagination("Danh sách gói PV", "price", true, "/RankCustomer", "/history_rank_customer");
        session.setAttribute("RANK_CUSTOMER_PAGINATION", rankCustomerPagniation);
        return new ModelAndView(DefaultAdminPagination.CONTAINER_FOLDER + rankCustomerPagniation.getViewName());
    }

    @RequestMapping(value = "/RankCustomer/DisplayPerPage/{displayPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeDisplayPerPageForRankCustomerView(@PathVariable("displayPerPage") int displayPerPage, ModelMap mm) {
        HistoryPagination rankCustomerPagniation = (HistoryPagination) session.getAttribute("RANK_CUSTOMER_PAGINATION");
        if (rankCustomerPagniation != null) {
            rankCustomerPagniation.setDisplayPerPage(displayPerPage);

        }
        return rankCustomerView(rankCustomerPagniation);
    }

    @RequestMapping(value = "/RankCustomer/OrderData/{orderBy}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView orderByRankCustomerView(@PathVariable("orderBy") String orderBy, ModelMap mm) {
        HistoryPagination rankCustomerPagniation = (HistoryPagination) session.getAttribute("RANK_CUSTOMER_PAGINATION");
        if (rankCustomerPagniation != null) {
            if (rankCustomerPagniation.getOrderColmn().equals(orderBy)) {
                rankCustomerPagniation.setAsc(!rankCustomerPagniation.isAsc());
            }
            rankCustomerPagniation.setOrderColmn(orderBy);
        }
        return rankCustomerView(rankCustomerPagniation);
    }

    @RequestMapping(value = "/RankCustomer/GoTo/{page}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView gotoRankCustomerView(@PathVariable("page") int page, ModelMap mm) {
        HistoryPagination rankCustomerPagniation = (HistoryPagination) session.getAttribute("RANK_CUSTOMER_PAGINATION");
        if (rankCustomerPagniation != null) {
            rankCustomerPagniation.setCurrentPage(page);
        }
        return rankCustomerView(rankCustomerPagniation);
    }

    @RequestMapping(value = "/RankCustomer/Search", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView searchRankCustomerView(@RequestBody Map map) {
        String searchString = (String) map.get("searchString");
        if (StringUtils.isEmpty(searchString)) {
            return rankCustomerView(null);
        }
        List<String> keywords = (List) map.get("keywords");
        HistoryPagination rankCustomerPagniation = new HistoryPagination("Danh sách gói PV", "price", true, "/RankCustomer", "/history_rank_customer");
        rankCustomerPagniation.setSearchString(searchString);
        rankCustomerPagniation.setKeywords(keywords);
        return rankCustomerView(rankCustomerPagniation);
    }

    private ModelAndView rankCustomerView(HistoryPagination rankCustomerPagniation) {
        if (rankCustomerPagniation == null) {
            rankCustomerPagniation = new HistoryPagination("Danh sách gói PV", "price", true, "/RankCustomer", "/history_rank_customer");
        }
        rCFacade.pageData(rankCustomerPagniation);
        session.setAttribute("RANK_CUSTOMER_PAGINATION", rankCustomerPagniation);
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + rankCustomerPagniation.getViewName());
    }

    @RequestMapping(value = "/RankCustomer/ViewInsert", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getRankCustomerViewInsert() {
        HistoryPagination rankCustomerPagniation = (HistoryPagination) session.getAttribute("RANK_CUSTOMER_PAGINATION");
        if (rankCustomerPagniation == null) {
            rankCustomerPagniation = new HistoryPagination("Danh sách gói PV", "id", false, "/RankCustomer", "/history_rank_customer");
        }
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + rankCustomerPagniation.getInsertViewName());
    }

    //CheckAward
    @RequestMapping(value = "/CheckAward", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getDefaultCheckAwardView(ModelMap mm) {
        HistoryPagination checkAwardPagniation = new HistoryPagination("Danh sách các gói thưởng", "id", true, "/CheckAward", "/history_check_award");
        session.setAttribute("CHECK_AWARD_PAGINATION", checkAwardPagniation);
        return new ModelAndView(DefaultAdminPagination.CONTAINER_FOLDER + checkAwardPagniation.getViewName());
    }

    @RequestMapping(value = "/CheckAward/DisplayPerPage/{displayPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeDisplayPerPageForCheckAwardView(@PathVariable("displayPerPage") int displayPerPage, ModelMap mm) {
        HistoryPagination checkAwardPagniation = (HistoryPagination) session.getAttribute("CHECK_AWARD_PAGINATION");
        if (checkAwardPagniation != null) {
            checkAwardPagniation.setDisplayPerPage(displayPerPage);

        }
        return checkAwardView(checkAwardPagniation);
    }

    @RequestMapping(value = "/CheckAward/OrderData/{orderBy}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView orderByCheckAwardView(@PathVariable("orderBy") String orderBy, ModelMap mm) {
        HistoryPagination checkAwardPagniation = (HistoryPagination) session.getAttribute("CHECK_AWARD_PAGINATION");
        if (checkAwardPagniation != null) {
            if (checkAwardPagniation.getOrderColmn().equals(orderBy)) {
                checkAwardPagniation.setAsc(!checkAwardPagniation.isAsc());
            }
            checkAwardPagniation.setOrderColmn(orderBy);
        }
        return checkAwardView(checkAwardPagniation);
    }

    @RequestMapping(value = "/CheckAward/GoTo/{page}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView gotoCheckAwardView(@PathVariable("page") int page, ModelMap mm) {
        HistoryPagination checkAwardPagniation = (HistoryPagination) session.getAttribute("CHECK_AWARD_PAGINATION");
        if (checkAwardPagniation != null) {
            checkAwardPagniation.setCurrentPage(page);
        }
        return checkAwardView(checkAwardPagniation);
    }

    @RequestMapping(value = "/CheckAward/Search", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView searchCheckAwardView(@RequestBody Map map) {
        String searchString = (String) map.get("searchString");
        if (StringUtils.isEmpty(searchString)) {
            return checkAwardView(null);
        }
        List<String> keywords = (List) map.get("keywords");
        HistoryPagination checkAwardPagniation = new HistoryPagination("Danh sách các gói thưởng", "id", true, "/CheckAward", "/history_check_award");
        checkAwardPagniation.setSearchString(searchString);
        checkAwardPagniation.setKeywords(keywords);
        return checkAwardView(checkAwardPagniation);
    }

    private ModelAndView checkAwardView(HistoryPagination checkAwardPagniation) {
        if (checkAwardPagniation == null) {
            checkAwardPagniation = new HistoryPagination("Danh sách các gói thưởng", "id", true, "/CheckAward", "/history_check_award");
        }
        cAFacade.pageData(checkAwardPagniation);
        session.setAttribute("CHECK_AWARD_PAGINATION", checkAwardPagniation);
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + checkAwardPagniation.getViewName());
    }

    //Not used Pinsys
    @RequestMapping(value = "/NotUsedPinSys", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getDefaultPinSysView(ModelMap mm) {
        HistoryPagination pinSysPagniation = new HistoryPagination("Danh sách mã PIN chưa sử dụng", "createdDate", false, "/NotUsedPinSys", "/history_not_used_pin_sys", "/history_insert_pin_sys_modal");
        session.setAttribute("NOT_USED_PIN_SYS_PAGINATION", pinSysPagniation);
        return new ModelAndView(DefaultAdminPagination.CONTAINER_FOLDER + pinSysPagniation.getViewName());
    }

    @RequestMapping(value = "/NotUsedPinSys/DisplayPerPage/{displayPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeDisplayPerPageForPinSysView(@PathVariable("displayPerPage") int displayPerPage, ModelMap mm) {
        HistoryPagination pinSysPagniation = (HistoryPagination) session.getAttribute("NOT_USED_PIN_SYS_PAGINATION");
        if (pinSysPagniation != null) {
            pinSysPagniation.setDisplayPerPage(displayPerPage);

        }
        return notUsedPinSysView(pinSysPagniation);
    }

    @RequestMapping(value = "/NotUsedPinSys/OrderData/{orderBy}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView orderByPinSysView(@PathVariable("orderBy") String orderBy, ModelMap mm) {
        HistoryPagination pinSysPagniation = (HistoryPagination) session.getAttribute("NOT_USED_PIN_SYS_PAGINATION");
        if (pinSysPagniation != null) {
            if (pinSysPagniation.getOrderColmn().equals(orderBy)) {
                pinSysPagniation.setAsc(!pinSysPagniation.isAsc());
            }
            pinSysPagniation.setOrderColmn(orderBy);
        }
        return notUsedPinSysView(pinSysPagniation);
    }

    @RequestMapping(value = "/NotUsedPinSys/GoTo/{page}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView gotoPinSysView(@PathVariable("page") int page, ModelMap mm) {
        HistoryPagination pinSysPagniation = (HistoryPagination) session.getAttribute("NOT_USED_PIN_SYS_PAGINATION");
        if (pinSysPagniation != null) {
            pinSysPagniation.setCurrentPage(page);
        }
        return notUsedPinSysView(pinSysPagniation);
    }

    @RequestMapping(value = "/NotUsedPinSys/Search", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView searchNotUsedPinSysView(@RequestBody Map map) {
        String searchString = (String) map.get("searchString");
        if (StringUtils.isEmpty(searchString)) {
            return notUsedPinSysView(null);
        }
        List<String> keywords = (List) map.get("keywords");
        HistoryPagination pinSysPagniation = new HistoryPagination("Danh sách mã PIN chưa sử dụng", "createdDate", false, "/NotUsedPinSys", "/history_not_used_pin_sys", "/history_insert_pin_sys_modal");
        pinSysPagniation.setSearchString(searchString);
        pinSysPagniation.setKeywords(keywords);
        return notUsedPinSysView(pinSysPagniation);
    }

    private ModelAndView notUsedPinSysView(HistoryPagination pinSysPagniation) {
        if (pinSysPagniation == null) {
            pinSysPagniation = new HistoryPagination("Danh sách mã PIN chưa sử dụng", "createdDate", false, "/NotUsedPinSys", "/history_not_used_pin_sys", "/history_insert_pin_sys_modal");
        }
        pFacade.pageDataNotUsed(pinSysPagniation);
        session.setAttribute("NOT_USED_PIN_SYS_PAGINATION", pinSysPagniation);
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + pinSysPagniation.getViewName());
    }

    @RequestMapping(value = "/NotUsedPinSys/ViewInsert", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getNotUsedPinSysViewInsert() {
        HistoryPagination pinSysPagniation = (HistoryPagination) session.getAttribute("NOT_USED_PIN_SYS_PAGINATION");
        if (pinSysPagniation == null) {
            pinSysPagniation = new HistoryPagination("Danh sách mã PIN chưa sử dụng", "createdDate", false, "/NotUsedPinSys", "/history_not_used_pin_sys", "/history_insert_pin_sys_modal");
        }
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + pinSysPagniation.getInsertViewName());
    }

    @RequestMapping(value = "/NotUsedPinSys/Insert", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView insertPinSys(@RequestParam(value = "rankCustomerId") Integer rankCustomerId,
            @RequestParam(value = "count") Integer count, ModelMap mm) {
        System.out.println("{RECEIVED}");
        ModelAndView mAV = new ModelAndView(DefaultAdminPagination.MESSAGE_FOLDER + MessagePagination.MESSAGE_VIEW);
        MessagePagination mP;
        Integer result;
        try {
            result = pFacade.insertPinSys(count, rankCustomerId, (Integer) session.getAttribute("ADMIN_ID"));
        } catch (Exception e) {
            mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi! Vui lòng thử lại sau!");
            mm.put("MESSAGE_PAGINATION", mP);
            return mAV;
        }
        switch (result) {
            case 1: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_SUCCESS, "thành công", "Đã thêm mới " + count + " mã PIN!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            default: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi! Vui lòng thử lại sau!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
        }
    }

    //Used pin sys

    @RequestMapping(value = "/UsedPinSys", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getDefaultUsedPinSysView(ModelMap mm) {
        HistoryPagination pinSysPagniation = new HistoryPagination("Lịch sử nạp mã pin", "usedDate", false, "/UsedPinSys", "/history_used_pin_sys", "/history_insert_pin_sys_modal");
        session.setAttribute("USED_PIN_SYS_PAGINATION", pinSysPagniation);
        return new ModelAndView(DefaultAdminPagination.CONTAINER_FOLDER + pinSysPagniation.getViewName());
    }

    @RequestMapping(value = "/UsedPinSys/DisplayPerPage/{displayPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeDisplayPerPageForUsedPinSysView(@PathVariable("displayPerPage") int displayPerPage, ModelMap mm) {
        HistoryPagination pinSysPagniation = (HistoryPagination) session.getAttribute("USED_PIN_SYS_PAGINATION");
        if (pinSysPagniation != null) {
            pinSysPagniation.setDisplayPerPage(displayPerPage);

        }
        return usedPinSysView(pinSysPagniation);
    }

    @RequestMapping(value = "/UsedPinSys/OrderData/{orderBy}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView orderByUsedPinSysView(@PathVariable("orderBy") String orderBy, ModelMap mm) {
        HistoryPagination pinSysPagniation = (HistoryPagination) session.getAttribute("USED_PIN_SYS_PAGINATION");
        if (pinSysPagniation != null) {
            if (pinSysPagniation.getOrderColmn().equals(orderBy)) {
                pinSysPagniation.setAsc(!pinSysPagniation.isAsc());
            }
            pinSysPagniation.setOrderColmn(orderBy);
        }
        return usedPinSysView(pinSysPagniation);
    }

    @RequestMapping(value = "/UsedPinSys/GoTo/{page}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView gotoUsedPinSysView(@PathVariable("page") int page, ModelMap mm) {
        HistoryPagination pinSysPagniation = (HistoryPagination) session.getAttribute("USED_PIN_SYS_PAGINATION");
        if (pinSysPagniation != null) {
            pinSysPagniation.setCurrentPage(page);
        }
        return usedPinSysView(pinSysPagniation);
    }

    @RequestMapping(value = "/UsedPinSys/Search", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView searchUsedPinSysView(@RequestBody Map map) {
        String searchString = (String) map.get("searchString");
        if (StringUtils.isEmpty(searchString)) {
            return usedPinSysView(null);
        }
        List<String> keywords = (List) map.get("keywords");
        HistoryPagination pinSysPagniation = new HistoryPagination("Lịch sử nạp mã pin", "usedDate", false, "/UsedPinSys", "/history_used_pin_sys", "/history_insert_pin_sys_modal");
        pinSysPagniation.setSearchString(searchString);
        pinSysPagniation.setKeywords(keywords);
        return usedPinSysView(pinSysPagniation);
    }

    private ModelAndView usedPinSysView(HistoryPagination pinSysPagniation) {
        if (pinSysPagniation == null) {
            pinSysPagniation = new HistoryPagination("Lịch sử nạp mã pin", "usedDate", false, "/UsedPinSys", "/history_used_pin_sys", "/history_insert_pin_sys_modal");
        }
        pFacade.pageDataUsedPinSys(pinSysPagniation);
        session.setAttribute("USED_PIN_SYS_PAGINATION", pinSysPagniation);
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + pinSysPagniation.getViewName());
    }

}
