package com.resources.controller;

import com.resources.facade.AdminFacade;
import com.resources.facade.CustomerFacade;
import com.resources.facade.CustomerRankCustomerFacade;
import com.resources.facade.HistoryAwardFacade;
import com.resources.entity.Admin;
import com.resources.function.CustomFunction;
import com.resources.pagination.admin.DefaultAdminPagination;
import com.resources.pagination.admin.MessagePagination;
import com.resources.utils.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/Admin")
public class AdminController {

    private final AdminFacade adminFacade;
    private final HistoryAwardFacade hitoryFacade;
    private final CustomerRankCustomerFacade customerRankCustomerFacade;
    private final CustomerFacade customerFacade;

    public AdminController() {
        adminFacade = new AdminFacade();
        hitoryFacade = new HistoryAwardFacade();
        customerRankCustomerFacade = new CustomerRankCustomerFacade();
        customerFacade = new CustomerFacade();
    }

    @RequestMapping(value = {"", "/Home"}, method = RequestMethod.GET)
    public ModelAndView getHomeView(ModelMap mm) {
        mm.put("HISTORY_AWARD_MAP", hitoryFacade.reportAllAwardByMonth(2015));
        mm.put("HISTORY_COMISSION_MAP", hitoryFacade.reportAllComissionByMonth(2015));
        mm.put("HISTORY_TOTAL_AWARD_YEAR", hitoryFacade.reportAllTotalAwardInCurrentYear());
        mm.put("TOTAL_IN_CURRENT_MONTH", hitoryFacade.getTotalInInCurrentMonth());
        mm.put("TOTAL_IN_CURRENT_YEAR", hitoryFacade.getTotalInInCurrentYear());
        mm.put("TOTAL_OUT_CURRENT_MONTH", hitoryFacade.getTotalOutInCurrentMonth());
        mm.put("TOTAL_OUT_CURRENT_YEAR", hitoryFacade.getTotalOutInCurrentYear());
        mm.put("TOTAL_USER_CURRENT_MONTH", hitoryFacade.countNewUserInCurrentMonth());
        mm.put("NEWEST_DEPOSIT", customerRankCustomerFacade.getNewestDeposit());
        mm.put("NEWEST_AWARD", customerRankCustomerFacade.getNewestAward());
        mm.put("NEWEST_USER", customerFacade.getNewestUser());
        mm.put("TOP_5_DEPOSIT_MONTH", customerRankCustomerFacade.getTop5DepositPVInMonth());
        mm.put("TOP_5_DEPOSIT_YEAR", customerRankCustomerFacade.getTop5DepositPVInYear());
        mm.put("TOP_5_AWARD_MONTH", hitoryFacade.getTop5AwardInMonth());
        mm.put("TOP_5_AWARD_YEAR", hitoryFacade.getTop5AwardInYear());
        return new ModelAndView("admin");
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public ModelAndView getLoginView(ModelMap mm) {
        return new ModelAndView("includes/admin/login");
    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView login(@RequestBody Admin admin, ModelMap mm, HttpServletRequest request) {
        ModelAndView mAV = new ModelAndView(DefaultAdminPagination.MESSAGE_FOLDER + MessagePagination.MESSAGE_VIEW);
        MessagePagination messagePagination;
        if (StringUtils.isEmpty(admin.getUserName()) || StringUtils.isEmpty(admin.getPassword())) {
            messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_WARNING, "Chú ý", "Vui lòng điền tên đăng nhập và mật khẩu!");
            mm.put("MESSAGE_PAGINATION", messagePagination);
            return mAV;
        }
        admin.setPassword(CustomFunction.md5(admin.getPassword()));
        try {
            admin = adminFacade.login(admin);
            if (admin == null || admin.getIsDelete()) {
                messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Sai tên đăng nhập hoặc mật khẩu!");
                mm.put("MESSAGE_PAGINATION", messagePagination);
                return mAV;
            }
            if (!admin.getIsActive()) {
                messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Cảnh bảo", "Tài khoản của bạn đã bị khóa!");
                mm.put("MESSAGE_PAGINATION", messagePagination);
                return mAV;
            }
            request.getSession().setAttribute("ADMIN_ID", admin.getId());
            mm.put("REDIRECT_URL", "/Admin/Home");
            mAV = new ModelAndView(DefaultAdminPagination.REDIRECT_FOLDER + DefaultAdminPagination.REDIRECT_VIEW);
            return mAV;
        } catch (Exception e) {
            messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi! Thử lại sau!");
            mm.put("MESSAGE_PAGINATION", messagePagination);
            return mAV;
        }
    }

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView register(@RequestBody Admin admin, ModelMap mm, HttpServletRequest request) {
        ModelAndView mAV = new ModelAndView(DefaultAdminPagination.MESSAGE_FOLDER + MessagePagination.MESSAGE_VIEW);
        MessagePagination mP;
        int result;
        try {
            result = adminFacade.create(admin);
        } catch (Exception e) {
            mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi! Thử lại sau!");
            mm.put("MESSAGE_PAGINATION", mP);
            return mAV;
        }
        switch (result) {
            case 1: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi. Thử lại sau!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            case 2: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_WARNING, "Chú ý", "Yêu cầu nhập tất cả các thông tin được yêu cầu!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            case 3: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Tên đăng nhập đã tồn tại!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            case 4: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_SUCCESS, "thành công", "Đăng ký thành công!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            default: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi. Thử lại sau!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
        }
    }

    @RequestMapping(value = "/Delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView delete(@PathVariable Integer id, ModelMap mm, HttpSession session) {
        ModelAndView mAV = new ModelAndView(DefaultAdminPagination.MESSAGE_FOLDER + MessagePagination.MESSAGE_VIEW);
        MessagePagination mP;
        int myId = (int) session.getAttribute("ADMIN_ID");
        if (myId == id) {
            mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Bạn không thể thực hiện hành động này!");
            mm.put("MESSAGE_PAGINATION", mP);
            return mAV;
        }
        try {
            adminFacade.delete(id);
        } catch (Exception e) {
            mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi! Thử lại sau!");
            mm.put("MESSAGE_PAGINATION", mP);
            return mAV;
        }
        mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_SUCCESS, "thành công", "Xóa quản trị viên thành công!");
        mm.put("MESSAGE_PAGINATION", mP);
        return mAV;
    }
    @RequestMapping(value = "/Block/{id}/{status}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView block(@PathVariable Integer id,@PathVariable Boolean status, 
            ModelMap mm, HttpSession session) {
        ModelAndView mAV = new ModelAndView(DefaultAdminPagination.MESSAGE_FOLDER + MessagePagination.MESSAGE_VIEW);
        MessagePagination mP;
        int myId = (int) session.getAttribute("ADMIN_ID");
        if (myId == id) {
            mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Bạn không thể thực hiện hành động này!");
            mm.put("MESSAGE_PAGINATION", mP);
            return mAV;
        }
        try {
            adminFacade.block(id,status);
        } catch (Exception e) {
            mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi! Thử lại sau!");
            mm.put("MESSAGE_PAGINATION", mP);
            return mAV;
        }
        mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_SUCCESS, "thành công", "Khóa quản trị viên thành công!");
        mm.put("MESSAGE_PAGINATION", mP);
        return mAV;
    }

    @RequestMapping(value = "/Logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ModelAndView("includes/admin/login");
    }
}
