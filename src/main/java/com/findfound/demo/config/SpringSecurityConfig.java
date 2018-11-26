package com.findfound.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.findfound.demo.filter.CommonUtils;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
@Configuration
	@EnableWebSecurity
	public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private UserDetailsService  userServiceDetails;
		@Bean
		public BCryptPasswordEncoder encodePWD()
		{
			return new BCryptPasswordEncoder();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
			http.authorizeRequests().antMatchers("/api/**").authenticated().anyRequest().permitAll()
			  .and().formLogin().permitAll();
			  http.logout()
			  .logoutUrl("/perform_logout")
			  .logoutSuccessUrl("/");
			  
		}

		@Autowired
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			 auth.userDetailsService(userServiceDetails).passwordEncoder(encodePWD());
			 
		}

}
