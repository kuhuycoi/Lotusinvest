package com.resources.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class IndexAuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        String rootContext = request.getServletContext().getContextPath();
        Object sessionObj = request.getSession().getAttribute("CUSTOMER_ID");
        if (sessionObj == null) {
            response.getWriter().write("<script>window.location.href='" + rootContext + "/Home'</script>");
            return false;
        }
        return true;
    }
}
