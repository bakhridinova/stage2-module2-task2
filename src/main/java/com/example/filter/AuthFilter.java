package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.util.AttributeParameterHolder.SESSION_ATTRIBUTE_USER;
import static com.example.util.PageNavigation.LOGIN_PAGE;

@WebFilter("/user/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Object user = httpServletRequest.getSession().getAttribute(SESSION_ATTRIBUTE_USER);

        if (user == null) {
            ((HttpServletResponse) response).sendRedirect(LOGIN_PAGE);
        } else {
            chain.doFilter(request, response);
        }

    }
}