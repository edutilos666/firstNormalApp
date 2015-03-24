package com.edutilos.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer{

	 private static final Logger logger = LoggerFactory.getLogger(WebInitializer.class);

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// TODO Auto-generated method stub
		 logger.info("creating AnnotationConfigWebApplicationContext instance");
		 AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext(); 
		 
		 logger.info("registering config classes");
		 ctx.register(MvcConfig.class);
		 
		 logger.info("setting servletContext");
		 ctx.setServletContext(servletContext);
		 
		 logger.info("adding DispatcherServlet instance ");
		 Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx)); 
		 servlet.addMapping("/"); 
		 servlet.setLoadOnStartup(1);
	} 
/*	 
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		 logger.info("creating AnnotationConfigWebApplicationContext instance");
		 AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext(); 
		 
		 logger.info("registering config classes");
		 ctx.register(Config.class);
		 
		 logger.info("setting servletContext");
		 ctx.setServletContext(servletContext);
		 
		 logger.info("adding DispatcherServlet instance ");
		 Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx)); 
		 servlet.addMapping("/"); 
		 servlet.setLoadOnStartup(1);
	}*/
	
}
	