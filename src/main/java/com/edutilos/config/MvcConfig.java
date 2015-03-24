package com.edutilos.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.SessionThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.edutilos.validator.WorkerValidator;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.edutilos.controller", "com.edutilos.service"})
@EnableJpaRepositories(basePackages={"com.edutilos.repository"})
@EnableTransactionManagement
public class MvcConfig extends WebMvcConfigurerAdapter{
  
	 private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class); 
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/resources/**").addResourceLocations("/resources/"); 
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		 configurer.enable(); 
	}
	
	
	@Bean 
	public InternalResourceViewResolver generateViewResolver() {
		logger.info("generating view resolver");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver(); 
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver; 
	}
	
	@Bean(name="messageSource")
	public ReloadableResourceBundleMessageSource generateMessageSource() {
		logger.info("generating messageSource");
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource(); 
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("utf-8");
		return messageSource; 
	}

	@Bean(name="localeResolver")
	public SessionLocaleResolver generateLocaleResolver() {
		logger.info("generating localeResolver");
		SessionLocaleResolver localeResolver = new SessionLocaleResolver(); 
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver; 
	}
	
	@Bean(name="localeChangeInterceptor")
	public LocaleChangeInterceptor generateLocaleChangeInterceptor() {
		logger.info("generating localeChangeInterceptor");
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor(); 
		interceptor.setParamName("locale");
		return interceptor; 
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("adding localeChangeInterceptor to interceptorRegistry");
	     registry.addInterceptor(generateLocaleChangeInterceptor()); 
	     
	     logger.info("adding themeChangeInterceptor to registry");
	     registry.addInterceptor(generateThemeChangeInterceptor()); 
	}
	
	
	@Bean(name="dataSource")
	public DriverManagerDataSource generateDataSource() {
		 logger.info("generating  driverManagerDataSource");
		 DriverManagerDataSource dataSource = new DriverManagerDataSource(); 
		 dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		 dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		 dataSource.setUsername("root");
		 dataSource.setPassword("");
		 
		return dataSource; 
	}
	
	@Bean(name="jpaVendorAdapter")
	public EclipseLinkJpaVendorAdapter generateJpaVendorAdapter() {
		 logger.info("generating jpaVendorAdapter");
		 EclipseLinkJpaVendorAdapter jpaVendorAdapter = new EclipseLinkJpaVendorAdapter(); 
		 jpaVendorAdapter.setDatabase(Database.MYSQL);
		 jpaVendorAdapter.setGenerateDdl(true);
		 jpaVendorAdapter.setShowSql(true);
		return jpaVendorAdapter; 
	}
	
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean generateEntityManagerFactoryBean() {
		logger.info("generating EntityManagerFactoryBean");
		LocalContainerEntityManagerFactoryBean emFactoryBean = new LocalContainerEntityManagerFactoryBean(); 
		emFactoryBean.setDataSource(generateDataSource());
		emFactoryBean.setJpaVendorAdapter(generateJpaVendorAdapter());
		emFactoryBean.setPackagesToScan("com.edutilos.domain");
		
		Map<String,Object> props = new HashMap<String,Object>(); 
		 props.put("eclipselink.weaving", "false"); 
		
		
		emFactoryBean.setJpaPropertyMap(props);
		
		return emFactoryBean; 
	}
	
	
	@Bean(name="transactionManager")
	public JpaTransactionManager generateJpaTransactionManager() {
		return new JpaTransactionManager(); 
	}
	
	
	//adding workerValidator 
	@Bean(name="workerValidator")
	public WorkerValidator workerValidator() {
		return new WorkerValidator(); 
	}
	
	
	//adding theme support 
	@Bean(name="themeSource")
	public ResourceBundleThemeSource generateThemeSource() {
		logger.info("generating themeSource");
		ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource(); 
		themeSource.setBasenamePrefix("theme-");
		
		return themeSource; 
	}

	@Bean(name="themeChangeInterceptor")
	public ThemeChangeInterceptor generateThemeChangeInterceptor() {
		logger.info("generating themeChangeInterceptor");
		ThemeChangeInterceptor themeChangeInterceptor = new ThemeChangeInterceptor(); 
		themeChangeInterceptor.setParamName("theme");
		return themeChangeInterceptor; 
	}
	
/*	@Bean(name="themeResolver")
	public SessionThemeResolver generateThemeResolver() {
		logger.info("generating themeResolver");
		SessionThemeResolver themeResolver = new SessionThemeResolver(); 
		themeResolver.setDefaultThemeName("default");
		return themeResolver; 
	}*/
	
	@Bean(name="themeResolver")
	public CookieThemeResolver generateThemeResolver() {
		logger.info("generating themeResolver");
		CookieThemeResolver themeResolver = new CookieThemeResolver(); 
		themeResolver.setDefaultThemeName("default");
		return themeResolver; 
	}
}
