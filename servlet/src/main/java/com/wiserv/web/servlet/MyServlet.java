package com.wiserv.web.servlet;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import java.io.IOException;

public class MyServlet implements Servlet {


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("=======init=========");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        if(servletRequest instanceof HttpRequest) {

        }
        System.out.println(servletRequest);

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("******destroy**********");
    }
}
