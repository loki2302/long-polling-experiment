package me.loki2302;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MvcConfiguration.class);
        
        servletContext.addListener(new ContextLoaderListener(context));
        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "dispatcher", 
                new DispatcherServlet(context));
        dispatcher.setAsyncSupported(true);
               
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}