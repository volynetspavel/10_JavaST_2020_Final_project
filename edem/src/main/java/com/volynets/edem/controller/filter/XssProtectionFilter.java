package com.volynets.edem.controller.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.volynets.edem.controller.command.JspPath;

/**
 * XssProtectionFilter is used to protect the site from cross site scripting(xss).
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/*"})
public class XssProtectionFilter implements Filter {

    private static final String ERROR = "error";
    private static final String STATUS_CODE = "statusCode";

    private static final String JS_SCRIPT_BEGIN_TAG = "<script>";
    private static final String JS_SCRIPT_END_TAG = "</script>";

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String text = request.getParameter(parameterNames.nextElement()).toLowerCase();
            if(text.contains(JS_SCRIPT_BEGIN_TAG) || text.contains(JS_SCRIPT_END_TAG)){
                request.setAttribute(ERROR, "Brrr, XSS attack!!!");
                request.setAttribute(STATUS_CODE, 404);
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher(JspPath.ERROR.getUrl());
                dispatcher.forward(request, response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

}
