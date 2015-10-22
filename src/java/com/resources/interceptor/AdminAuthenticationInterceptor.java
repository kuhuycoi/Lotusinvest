package com.resources.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AdminAuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String rootContext = request.getServletContext().getContextPath();
        Object sessionObj = request.getSession().getAttribute("ADMIN_ID");
        if (sessionObj == null) {
            if (!uri.endsWith("/Login")) {
                response.getWriter().write("<script>window.location.href='" + rootContext + "/Admin/Login'</script>");
                return false;
            }
            if (uri.endsWith("/Logout")) {
                response.getWriter().write("<script>window.location.href='" + rootContext + "/Admin/Login'</script>");
                return false;
            }
        } else if (uri.endsWith("/Login")) {
            response.getWriter().write("<script>window.location.href='" + rootContext + "/Admin/Home'</script>");
            return false;
        }
        return true;
    }
}
