package com.pe.auth.jwt.authorization.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pe.auth.jwt.authorization.jwtFilter.JwtFilter;

@Configuration
public class JWTConfiguration {
	
	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {
		FilterRegistrationBean<JwtFilter> filterRegistrationBean=new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/api/v1/*");
		return filterRegistrationBean;
	}

}
