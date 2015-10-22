package com.resources.controller;

import com.resources.facade.CustomerFacade;
import com.resources.bean.CustomerNonActive;
import com.resources.entity.Customer;
import com.resources.function.CustomFunction;
import com.resources.pagination.index.*;
import com.resources.utils.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(value = "/Customer")
public class IndexCustomerController {

    @Autowired
    HttpSession session;
    private final CustomerFacade cFacade;
    private final CustomerPagination treeCustomerPagination;

    public IndexCustomerController() {
        cFacade = new CustomerFacade();
        treeCustomerPagination = new CustomerPagination("/customer_tree_customer", "/TreeCustomer");
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getLoginView(ModelMap mm) {
        return new ModelAndView(DefaultIndexPagination.AJAX_FOLDER + DefaultIndexPagination.LOGIN_VIEW);
    }

    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getRegisterView(ModelMap mm) {
        return new ModelAndView(DefaultIndexPagination.AJAX_FOLDER + DefaultIndexPagination.REGISTER_VIEW);
    }

    @RequestMapping(value = "/ResetPassword", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getResetPasswordView(ModelMap mm) {
        return new ModelAndView(DefaultIndexPagination.AJAX_FOLDER + DefaultIndexPagination.RESET_PASSWORD_VIEW);
    }

    @RequestMapping(value = "/ResetPassword", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView resetPasswordView(@RequestBody Customer cus, ModelMap mm, HttpServletRequest request) {
        ModelAndView mAV = new ModelAndView(MessagePagination.MESSAGE_FOLDER + MessagePagination.MESSAGE_VIEW);
        MessagePagination messagePagination;
        if (StringUtils.isEmpty(cus.getUserName()) || StringUtils.isEmpty(cus.getEmail())) {
            messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_WARNING, "Chú ý", "Vui lòng điền tên đăng nhập và email!");
            mm.put("MESSAGE_PAGINATION", messagePagination);
            return mAV;
        }
        try {
            Integer rs = 0;
            //chỗ này check username hoặc title giống với cus.getUserName() và email giống với cus.getEmail(). Viết vào CustomerFacade
            //Nếu tồn tại thì reset mật khẩu thành random 6 số, có cả chữ thì tốt rồi gửi mail về cus.getEmail(), gửi mail thất bại thì vẫn rs mk
            // rs = integer trả về tương ứng như sau 
            switch (rs) {
                case 1: {
                    messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Chú ý", "Tên đăng nhập hoặc email không hợp lệ!");
                    mm.put("MESSAGE_PAGINATION", messagePagination);
                    return mAV;
                }
                case 2: {
                    messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_WARNING, "Chú ý", "Mật khẩu đã được reset để đảm bảo an toàn cho tài khoản của bạn nhưng chưa được gửi về email!");
                    mm.put("MESSAGE_PAGINATION", messagePagination);
                    return mAV;
                }
                case 3: {
                    messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_SUCCESS, "Thành công", "Mật khẩu đã được reset và gửi về hòm thư của bạn!");
                    mm.put("MESSAGE_PAGINATION", messagePagination);
                    return mAV;
                }
            }
            request.getSession().setAttribute("CUSTOMER_ID", cus.getId());
            mm.put("REDIRECT_URL", "/Home");
            mAV = new ModelAndView(DefaultIndexPagination.REDIRECT_FOLDER + DefaultIndexPagination.REDIRECT_VIEW);
            return mAV;
        } catch (Exception e) {
            e.printStackTrace();
            messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi! Thử lại sau!");
            mm.put("MESSAGE_PAGINATION", messagePagination);
            return mAV;
        }
    }

    @RequestMapping(value = "/ChangePassword", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getChangePasswordView(ModelMap mm) {
        return new ModelAndView(DefaultIndexPagination.AJAX_FOLDER + DefaultIndexPagination.CHANGE_PASSWORD_VIEW);
    }

    @RequestMapping(value = "/ChangePassword", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView changePassword(@RequestBody Map map, ModelMap mm) {
        ModelAndView mAV = new ModelAndView(MessagePagination.MESSAGE_FOLDER + MessagePagination.MESSAGE_VIEW);
        MessagePagination messagePagination;
        String oldPassword = (String) map.get("oldPassword");
        String newPassword = (String) map.get("newPassword");
        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
            messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_WARNING, "Chú ý", "Vui lòng điền đầy đủ thông tin!");
            mm.put("MESSAGE_PAGINATION", messagePagination);
            return mAV;
        }
        System.out.println(oldPassword);
        System.out.println(newPassword);
        oldPassword = CustomFunction.md5(oldPassword);
        newPassword = CustomFunction.md5(newPassword);
        Integer cusid = (Integer) session.getAttribute("CUSTOMER_ID");
        try {
            Customer cus = (Customer) cFacade.find(cusid);
            if (!oldPassword.equals(cus.getPassword())) {
                messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Mật khẩu không chính xác!");
                mm.put("MESSAGE_PAGINATION", messagePagination);
                return mAV;
            }
            if (oldPassword.equals(newPassword)) {
                messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_SUCCESS, "Thành công", "Cập nhật mật khẩu thành công!");
                mm.put("MESSAGE_PAGINATION", messagePagination);
                return mAV;
            }
            cus.setPassword(newPassword);
            cFacade.edit(cus);
            messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_SUCCESS, "Thành công", "Cập nhật mật khẩu thành công!");
            mm.put("MESSAGE_PAGINATION", messagePagination);
            return mAV;
        } catch (Exception ex) {
            ex.printStackTrace();
            messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi! Thử lại sau!");
            mm.put("MESSAGE_PAGINATION", messagePagination);
            return mAV;
        }
    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView login(@RequestBody Customer cus, ModelMap mm, HttpServletRequest request) {
        ModelAndView mAV = new ModelAndView(MessagePagination.MESSAGE_FOLDER + MessagePagination.MESSAGE_VIEW);
        MessagePagination messagePagination;
        if (StringUtils.isEmpty(cus.getUserName()) || StringUtils.isEmpty(cus.getPassword())) {
            messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_WARNING, "Chú ý", "Vui lòng điền tên đăng nhập và mật khẩu!");
            mm.put("MESSAGE_PAGINATION", messagePagination);
            return mAV;
        }
        cus.setPassword(CustomFunction.md5(cus.getPassword()));
        try {
            cus = cFacade.login(cus);
            if (cus == null) {
                messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Sai tên đăng nhập hoặc mật khẩu!");
                mm.put("MESSAGE_PAGINATION", messagePagination);
                return mAV;
            }
            if (!cus.getIsActive()) {
                messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Cảnh bảo", "Tài khoản của bạn chưa được kích hoạt!");
                mm.put("MESSAGE_PAGINATION", messagePagination);
                return mAV;
            }
            request.getSession().setAttribute("CUSTOMER_ID", cus.getId());
            mm.put("REDIRECT_URL", "/Home");
            mAV = new ModelAndView(DefaultIndexPagination.REDIRECT_FOLDER + DefaultIndexPagination.REDIRECT_VIEW);
            return mAV;
        } catch (Exception e) {
            e.printStackTrace();
            messagePagination = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi! Thử lại sau!");
            mm.put("MESSAGE_PAGINATION", messagePagination);
            return mAV;
        }
    }

    @RequestMapping(value = "/Logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/ActiveCustomer/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView activeCustomer(@PathVariable(value = "id") Integer id, ModelMap mm) {
        ModelAndView mAV = new ModelAndView(DefaultIndexPagination.MESSAGE_FOLDER + MessagePagination.MESSAGE_VIEW);
        MessagePagination mP;
        Integer result;
        try {
            result = cFacade.activeCustomer(id);
        } catch (Exception e) {
            mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi! Vui lòng thử lại sau!");
            mm.put("MESSAGE_PAGINATION", mP);
            return mAV;
        }
        switch (result) {
            case 1: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_SUCCESS, "thành công", "Active thành công!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            case 2: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Nhánh cha đã đủ 2 người!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            case 3: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "ID người dùng không hợp lệ!");
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

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView register(@RequestBody CustomerNonActive customerNonActive, ModelMap mm) {
        ModelAndView mAV = new ModelAndView(DefaultIndexPagination.MESSAGE_FOLDER + MessagePagination.MESSAGE_VIEW);
        MessagePagination mP;
        customerNonActive.setUserName(cFacade.createUserName());
        int result;
        try {
            result = cFacade.create(customerNonActive);
        } catch (Exception ex) {
            ex.printStackTrace();
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
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Tên đăng nhập nhanh đã tồn tại");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            case 4: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Người chỉ định không hợp lệ");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            case 5: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Người giới thiệu không hợp lệ");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            case 6: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Yêu cầu người chỉ định và người giới thiệu phải trong cùng một hệ thống");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            case 7: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_SUCCESS, "thành công", "Đăng ký thành công, Username: " + customerNonActive.getUserName());
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

    //My account
    @RequestMapping(value = "/MyAccount", method = RequestMethod.GET)
    public ModelAndView myAccount(HttpServletRequest request) {
        return new ModelAndView("includes/index/ajax_content/customer_my_account");
    }

    //Edit Customer
    @RequestMapping(value = "/Edit", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView editCustomer(@RequestBody CustomerNonActive customerNonActive, ModelMap mm) {
        ModelAndView mAV = new ModelAndView(DefaultIndexPagination.MESSAGE_FOLDER + MessagePagination.MESSAGE_VIEW);
        MessagePagination mP;
        customerNonActive.setId((Integer) session.getAttribute("CUSTOMER_ID"));
        System.out.println(customerNonActive.getGender());
        int result;
        try {
            result = cFacade.edit(customerNonActive);
        } catch (Exception ex) {
            mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi. Thử lại sau!");
            mm.put("MESSAGE_PAGINATION", mP);
            return mAV;
        }
        switch (result) {
            case 1: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_SUCCESS, "thành công", "Cập nhật nhà phân phối thành công!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
            default: {
                mP = new MessagePagination(MessagePagination.MESSAGE_TYPE_ERROR, "Lỗi", "Đã xảy ra lỗi1. Thử lại sau!");
                mm.put("MESSAGE_PAGINATION", mP);
                return mAV;
            }
        }
    }

    //Tree Customer
    @RequestMapping(value = "/TreeCustomer", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getTreeCustomer(ModelMap mm, HttpSession session) {
        Integer children = (Integer) session.getAttribute("CUSTOMER_ID");
        List tree = null;
        try {
            tree = cFacade.getTreeCustomer(children, null);
        } catch (Exception ex) {
            Logger.getLogger(IndexCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mm.put("LIST_TREE", tree);
        return new ModelAndView(DefaultIndexPagination.AJAX_FOLDER + treeCustomerPagination.getViewName());
    }

    //CustomerForCustomer
    @RequestMapping(value = "/CustomerForCustomer", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getDefaultDistributorView(ModelMap mm) {
        CustomerPagination customerForCustomerPagination = new CustomerPagination("/customer_for_customer", "/CustomerForCustomer");
        session.setAttribute("INDEX_CUSTOMER_FOR_CUSTOMER_PAGINATION", customerForCustomerPagination);
        return new ModelAndView(DefaultIndexPagination.CONTAINER_FOLDER + customerForCustomerPagination.getViewName());
    }

    @RequestMapping(value = "/CustomerForCustomer/DisplayPerPage/{displayPerPage}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView displayPerPageForDistributorView(@PathVariable("displayPerPage") int displayPerPage, ModelMap mm, HttpSession session) {
        CustomerPagination customerForCustomerPagination = (CustomerPagination) session.getAttribute("INDEX_CUSTOMER_FOR_CUSTOMER_PAGINATION");
        if (customerForCustomerPagination != null) {
            customerForCustomerPagination.setDisplayPerPage(displayPerPage);

        }
        return distributorView(customerForCustomerPagination);
    }

    @RequestMapping(value = "/CustomerForCustomer/OrderData/{orderBy}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView orderByDistributorView(@PathVariable("orderBy") String orderBy, ModelMap mm, HttpSession session) {
        CustomerPagination customerForCustomerPagination = (CustomerPagination) session.getAttribute("INDEX_CUSTOMER_FOR_CUSTOMER_PAGINATION");
        if (customerForCustomerPagination != null) {
            if (customerForCustomerPagination.getOrderColmn().equals(orderBy)) {
                customerForCustomerPagination.setAsc(!customerForCustomerPagination.isAsc());
            }
            customerForCustomerPagination.setOrderColmn(orderBy);
        }
        return distributorView(customerForCustomerPagination);
    }

    @RequestMapping(value = "/CustomerForCustomer/GoTo/{page}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView gotoDistributorView(@PathVariable("page") int page, ModelMap mm, HttpSession session) {
        CustomerPagination customerForCustomerPagination = (CustomerPagination) session.getAttribute("INDEX_CUSTOMER_FOR_CUSTOMER_PAGINATION");
        if (customerForCustomerPagination != null) {
            customerForCustomerPagination.setCurrentPage(page);
        }
        return distributorView(customerForCustomerPagination);
    }

    private ModelAndView distributorView(CustomerPagination customerForCustomerPagination) {
        if (customerForCustomerPagination == null) {
            customerForCustomerPagination = new CustomerPagination("/customer_for_customer", "/CustomerForCustomer");
        }
        cFacade.pageData(customerForCustomerPagination, (Integer) session.getAttribute("CUSTOMER_ID"));
        session.setAttribute("INDEX_CUSTOMER_FOR_CUSTOMER_PAGINATION", customerForCustomerPagination);
        return new ModelAndView(DefaultIndexPagination.AJAX_FOLDER + customerForCustomerPagination.getViewName());
    }

    //Auto complete ParentId
    @RequestMapping(value = "/SearchParentId/{searchString}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView searchParentId(@PathVariable("searchString") String searchString, ModelMap mm) {
        mm.put("PARENTIDLIST", cFacade.findAllCustomerForParentId(searchString));
        return new ModelAndView(DefaultIndexPagination.AJAX_FOLDER + "/customer_parentid_list");
    }

    //Auto complete CustomerId
    @RequestMapping(value = "/SearchCustomerId/{searchString}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView searchCustomerId(@PathVariable("searchString") String searchString, ModelMap mm) {
        mm.put("PARENTIDLIST", cFacade.findAllCustomerForCustomerId(searchString, null));
        return new ModelAndView(DefaultIndexPagination.AJAX_FOLDER + "/customer_parentid_list");
    }

    //Auto complete CustomerId
    @RequestMapping(value = "/SearchCustomerId/{searchString}/{parentName}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView searchCustomerId(@PathVariable("searchString") String searchString, @PathVariable("parentName") String parentName, ModelMap mm) {
        mm.put("PARENTIDLIST", cFacade.findAllCustomerForCustomerId(searchString, parentName));
        return new ModelAndView(DefaultIndexPagination.AJAX_FOLDER + "/customer_parentid_list");
    }
}
