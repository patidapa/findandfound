package com.findfound.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.findfound.demo.filter.MyFilter;

@Configuration
public class FandFFilterConfig {

	@Bean
	public FilterRegistrationBean <MyFilter> registrationBean()
	{
		FilterRegistrationBean registrationBean=new FilterRegistrationBean<>();
		
		registrationBean.setFilter(new MyFilter());
		registrationBean.addUrlPatterns("/*");
		
		return registrationBean;
		
	}
}
