package com.edutilos.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@ComponentScan(basePackages={"com.edutilos.controller", "com.edutilos.domain"})
@EnableWebMvc
public class Config {
  private static final Logger logger = LoggerFactory.getLogger(Config.class); 
  
   @Bean public InternalResourceViewResolver generateViewResolver() {
	     logger.info("generating view resolver");
	     InternalResourceViewResolver resolver = new InternalResourceViewResolver(); 
	     resolver.setPrefix("/WEB-INF/views/");
	     resolver.setSuffix(".jsp");
	     resolver.setViewClass(JstlView.class);
	   return resolver; 
   }
   
	 @Bean(name="messageSource")
	 public ReloadableResourceBundleMessageSource generateMessageSource() {
		 ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		 source.setBasename("classpath:messages");
		 source.setDefaultEncoding("utf-8");
		 return source; 
	 }
}
