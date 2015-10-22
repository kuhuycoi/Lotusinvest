package com.resources.controller;

import com.resources.facade.AdminFacade;
import com.resources.entity.Admin;
import com.resources.pagination.admin.AdminPagination;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/Admin/Permission")
public class AdminPermissionController {

    @Autowired
    private HttpSession session;
    private final AdminFacade aFacade;

    public AdminPermissionController() {
        this.aFacade = new AdminFacade();
    }

    //ListAdmin    
    @RequestMapping(value = "/ListAdmin", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getDefaultListAdminView(ModelMap mm) {
        AdminPagination adminPagination = new AdminPagination("/permission_list_admin", "/ListAdmin");
        session.setAttribute("LIST_ADMIN_PAGINATION", adminPagination);
        return new ModelAndView(DefaultAdminPagination.CONTAINER_FOLDER + adminPagination.getViewName());
    }

    @RequestMapping(value = "/ListAdmin/DisplayPerPage/{displayPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView changeDisplayPerPageForListAdminView(@PathVariable("displayPerPage") int displayPerPage, ModelMap mm) {
        AdminPagination adminPagination = (AdminPagination) session.getAttribute("LIST_ADMIN_PAGINATION");
        if (adminPagination != null) {
            adminPagination.setDisplayPerPage(displayPerPage);
        }
        return listAdminView(adminPagination);
    }

    @RequestMapping(value = "/ListAdmin/OrderData/{orderBy}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView orderByListAdminView(@PathVariable("orderBy") String orderBy, ModelMap mm) {
        AdminPagination adminPagination = (AdminPagination) session.getAttribute("LIST_ADMIN_PAGINATION");
        if (adminPagination != null) {
            if (adminPagination.getOrderColmn().equals(orderBy)) {
                adminPagination.setAsc(!adminPagination.isAsc());
            }
            adminPagination.setOrderColmn(orderBy);
        }
        return listAdminView(adminPagination);
    }

    @RequestMapping(value = "/ListAdmin/GoTo/{page}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView gotoListAdminView(@PathVariable("page") int page, ModelMap mm) {
        AdminPagination adminPagination = (AdminPagination) session.getAttribute("LIST_ADMIN_PAGINATION");
        if (adminPagination != null) {
            adminPagination.setCurrentPage(page);
        }
        return listAdminView(adminPagination);
    }

    @RequestMapping(value = "/ListAdmin/Search", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView searchDistributorView(@RequestBody Map map) {
        String searchString = (String) map.get("searchString");
        if (StringUtils.isEmpty(searchString)) {
            return listAdminView(null);
        }
        List<String> keywords = (List) map.get("keywords");
        AdminPagination adminPagination = new AdminPagination("/permission_list_admin", "/ListAdmin");
        adminPagination.setSearchString(searchString);
        adminPagination.setKeywords(keywords);
        return listAdminView(adminPagination);
    }

    private ModelAndView listAdminView(AdminPagination adminPagination) {
        if (adminPagination == null) {
            adminPagination = new AdminPagination("/permission_list_admin", "/ListAdmin");
        }
        aFacade.pageData(adminPagination);
        session.setAttribute("CUSTOMER_RANK_CUSTOMER_PAGINATION", adminPagination);
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + adminPagination.getViewName());
    }

    @RequestMapping(value = "/ListAdmin/ViewInsert", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getListAdminViewInsert() {
        AdminPagination adminPagination = (AdminPagination) session.getAttribute("LIST_ADMIN_PAGINATION");
        if (adminPagination == null) {
            adminPagination = new AdminPagination("/permission_list_admin", "/ListAdmin");
        }
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + adminPagination.getInsertViewName());
    }

    @RequestMapping(value = "/ListAdmin/Insert", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView insertAdmin(@RequestBody Admin admin, ModelMap mm) {
        ModelAndView mAV = new ModelAndView(DefaultAdminPagination.MESSAGE_FOLDER + MessagePagination.MESSAGE_VIEW);
        MessagePagination mP;
        admin.setPassword("123456");
        int result;
        try {
            result = aFacade.create(admin);
        } catch (Exception ex) {
            mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi. Thử lại sau!");
            mm.put("MESSAGE_PAGINATION", mP);
            return mAV;
        }
        switch (result) {
            case 1: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi. Người dùng không hợp lệ!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            case 2: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_WARNING, "Chú ý", "Yêu cầu nhập tất cả các thông tin được yêu cầu!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            case 3: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Tên đăng nhập đã tồn tại");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            case 4: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_SUCCESS, "thành công", "Đăng ký thành công");
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

    @RequestMapping(value = "/ListAdmin/ViewInsert/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getListAdminViewInsert(@PathVariable(value = "userName") String userName, ModelMap mm) {
        mm.put("USERNAME", userName);
        AdminPagination adminPagination = (AdminPagination) session.getAttribute("LIST_ADMIN_PAGINATION");
        if (adminPagination == null) {
            adminPagination = new AdminPagination("/permission_list_admin", "/ListAdmin");
        }
        return new ModelAndView(DefaultAdminPagination.AJAX_FOLDER + adminPagination.getInsertViewName());
    }

}
