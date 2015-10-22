package com.resources.function;

import com.resources.facade.AdminFacade;
import com.resources.facade.CustomerFacade;
import com.resources.facade.CustomerTypeFacade;
import com.resources.facade.HistoryAwardFacade;
import com.resources.facade.ProvincialAgenciesFacade;
import com.resources.facade.RankCustomersFacade;
import com.resources.facade.RankNowFacade;
import com.google.gson.Gson;
import com.resources.bean.CustomerNonActive;
import com.resources.bean.CustomerTree;
import com.resources.entity.Admin;
import com.resources.entity.Customer;
import com.resources.entity.Module;
import com.resources.entity.RankNow;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CustomFunction {

    private static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat("###,###,### VNĐ");
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

    public static String formatCurrency(BigDecimal input) {
        return input == null ? null : CURRENCY_FORMAT.format(input.multiply(BigDecimal.valueOf(10000)));
    }

    public static boolean isZero(BigDecimal input) {
        return BigDecimal.ZERO.compareTo(input) == 0;
    }

    public static String formatPercent(Integer input) {
        return input == null ? null : String.valueOf(input) + "%";
    }

    public static String formatPercentTwoInput(BigDecimal input1, BigDecimal input2) {
        if (BigDecimal.ZERO.compareTo(input2) == 0) {
            return "0%";
        }
        return input1.multiply(BigDecimal.valueOf(100)).divide(input2, 2, RoundingMode.CEILING).toString() + "%";
    }

    public static Date addDate(Date date, Integer dateAdd) {
        Calendar c = Calendar.getInstance();
        if (date == null) {
            c.add(Calendar.DATE, -100);
        } else {
            c.setTime(date);
            c.add(Calendar.DATE, dateAdd);
        }
        return c.getTime();
    }

    public static int compareCurrentDate(Date date1) {
        if (date1 == null) {
            return 1;
        }
        if (currentTime().before(date1)) {
            return -1;
        } else if (currentTime().after(date1)) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int size(Collection c) {
        return c == null ? 0 : c.size();
    }

    public static Date currentTime() {
        return new Date();
    }

    public static String[] split(String input, String match) {
        return input.split(match);
    }

    public static String replace(String input, String match, String replace) {
        return input.replace(match, replace);
    }

    public static boolean equal(String input1, String input2) {
        return input1.equals(input2);
    }

    public static boolean equalIgnoreCase(String input1, String input2) {
        return input1.equalsIgnoreCase(input2);
    }

    public static String checkNameLength(String input, Integer length) {
        return input.length() > length + 3 ? input.substring(0, length) + "..." : input;
    }

    public static String tranformQuantityToStatus(Integer quantity) {
        return quantity > 0 ? "In stock" : "Out of stock";
    }

    public static String tranformStatus(Integer status) {
        return status == 0 ? "Unavailable" : "Available";
    }

    public static String formatTime(Date date) {
        return date == null ? "Unavailable" : SIMPLE_DATE_FORMAT.format(date);
    }

    public static String getOrderTrClass(Integer status) {
        switch (status) {
            case 0: {
                return "warning";
            }
            case 1: {
                return "info";
            }
            case 2: {
                return "success";
            }
            default: {
                return "danger";
            }
        }
    }

    public static String tranformOrderStatus(Integer status) {
        switch (status) {
            case 0: {
                return "Waiting";
            }
            case 1: {
                return "Processing";
            }
            case 2: {
                return "Done";
            }
            default: {
                return "Expired";
            }
        }
    }

    public static String getJSON(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static List<Module> filterModuleList(List<Module> moduleList, Module module) {
        return moduleList.stream().filter(m -> {
            return m.getModule() == module;
        }).collect(Collectors.toList());
    }

    public static List<CustomerTree> filterCustomerList(List<CustomerTree> customerList, Integer parentId) {
        return customerList.stream().filter(c -> {
            return Objects.equals(c.getBoss(), parentId);
        }).collect(Collectors.toList());
    }

    public static String buildTreeCustomer(List<CustomerTree> customerList, Integer first_id) {
        List<CustomerTree> list = filterCustomerList(customerList, first_id);
        StringBuilder sB = new StringBuilder();
        sB.append("<ul>");
        list.stream().forEach((c) -> {
            String popOver = "<span class='popover-dismiss' data-toggle='popover' data-placement='right' data-title='" + c.getName() + "' ";
            popOver += "data-content='<b>Username:</b> " + c.getUserName() + "<br/>"
                    + "<b>Chức vụ:</b> " + c.getLevelName() + "</br>"
                    + "<b>Chu kỳ:</b> " + c.getCircle() + "</br>"
                    + "<b>Nhánh trái:</b> " + formatCurrency(c.getpVLeft()) + "<br/>"
                    + "<b>Nhánh phải:</b> " + formatCurrency(c.getpVRight()) + "'>";
            if (!filterCustomerList(customerList, c.getKey()).isEmpty()) {
                if (c.getLevel() == 9) {
                    sB.append("<li class='hide-item'><a>");
                    sB.append("<span class='fa fa-plus-square-o'></span>&nbsp;");
                } else {
                    sB.append("<li><a>");
                    sB.append("<span class='fa fa-minus-square-o'></span>&nbsp;");
                }
            } else {
                sB.append("<li><a>");
                sB.append("<span class='fa fa-user'></span>&nbsp;");
            }
            sB.append(popOver);
            sB.append(c.getName()).append(" - ").append(c.getUserName())
                    .append(" <i class='text-danger'>(Level ").append(c.getLevel()).append(")</i>").append("</span></a>")
                    .append(buildTreeCustomer(customerList, c.getKey()))
                    .append("</li>");
        });
        sB.append("</ul>");
        return sB.toString();
    }

    public static CustomerNonActive findDistributorById(Integer id) {
        return new CustomerFacade().findDistributorById(id);
    }

    public static RankNow findRankNowByCustomer(Customer customer) {
        return new RankNowFacade().findRankNowByCusID(customer);
    }

    public static List findAllAvailableCustomerType() {
        return new CustomerTypeFacade().findAllAvailableCustomerType();
    }

    public static List findAllAvailableProvincialAgencies() {
        return new ProvincialAgenciesFacade().findAllAvailableProvincialAgencies();
    }

    public static List findAllAvailableRankCustomer() {
        return new RankCustomersFacade().findAllAvailableRankCustomer();
    }

    public static Admin findAdminById(Integer id) {
        return (Admin) (new AdminFacade().find(id));
    }

    public static List findAllHistoryAwardYear() {
        return new HistoryAwardFacade().findAllHistoryAwardYear();
    }

    public static String md5(String input) {
        MessageDigest md;
        StringBuffer sB;
        String output = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            sB = new StringBuffer();
            for (byte b : digest) {
                sB.append(String.format("%02x", b & 0xff));
            }
            output = sB.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CustomFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
}
