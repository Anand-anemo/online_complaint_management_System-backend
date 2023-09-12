package com.project.crs.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Component
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtauthenticationentrypoint;
	
	//@Lazy
	@Autowired
	private JwtRequestFilter jwtrequestfilter;
	
	//@Lazy
	@Autowired
	private UserDetailsService jwtservice;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpsecurity) throws Exception {
		httpsecurity.cors();
		httpsecurity.csrf().disable().authorizeRequests()
		.antMatchers("/authenticate","/registerNewCustomer","/registerNewEngineer",
				"/deleteUser/{userName}",
				"/add/new/complaint",
				"/get/complaint/list",
				"/get/mycomplaints",
				"/get/managercomplaintlist",
				"/getComplaintById/{complaintId}",
				"/engineers",
				"/allUser","/engineers/forManager")
		.permitAll().antMatchers(HttpHeaders.ALLOW)
				.permitAll().anyRequest().authenticated()
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(jwtauthenticationentrypoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpsecurity.addFilterBefore(jwtrequestfilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticatnManagerBuilder) throws Exception {
		authenticatnManagerBuilder.userDetailsService(jwtservice).passwordEncoder(passwordEncoder());
	}

}
