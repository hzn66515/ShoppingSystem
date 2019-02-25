package com.hzn.sales.web.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    private final Logger logger= LoggerFactory.getLogger(LoginFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();
        System.out.println("requestURI::::"+requestURI);
        String contextPath = request.getContextPath();
        String loginStatus = (String) session.getAttribute("loginStatus");
        if (StringUtils.isEmpty(loginStatus))
        {
            String sellerUser = (String)session.getAttribute("sellerUser");
            String buyerUser = (String) session.getAttribute("buyerUser");
            if(!StringUtils.isEmpty(sellerUser))
                //卖家
                session.setAttribute("loginStatus","sellerUser");
            else if (!StringUtils.isEmpty(buyerUser))
                //买家
                session.setAttribute("loginStatus","buyerUser");
            else
                //游客
                session.setAttribute("loginStatus","visitor");
        }
        if (("/").equals(requestURI))
        {
            request.getRequestDispatcher("/index").forward(request,response);
        }
        else {
            filterChain.doFilter(request,response);
        }
    }

    public void destroy() {

    }
}
