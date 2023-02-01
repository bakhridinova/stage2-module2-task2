package com.example.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;

import static com.example.util.AttributeParameterHolder.CONTEXT_ATTRIBUTE_SERVLET_TIME_INIT;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(CONTEXT_ATTRIBUTE_SERVLET_TIME_INIT, LocalDateTime.now());
    }
}
