package com.milos.kindergarden.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.milos.kindergarden.serviceimplemtations.MyUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
		
	MyUserDetailsServiceImpl guardianDetailsService;
	
	public SecurityConfiguration(MyUserDetailsServiceImpl guardianDetailsService) {
		super();
		this.guardianDetailsService = guardianDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/","/index","/login*").permitAll()
			.antMatchers("/css/**","/img/**","/js/**","/scss/**","/vendor/**").permitAll()
			.antMatchers("/employee","/employee/**").hasRole("EMPLOYEE")
			.antMatchers("/teacher","/teacher/**").hasRole("TEACHER")
			.antMatchers("/guardian").hasRole("GUARDIAN")
			.anyRequest().authenticated()
			.and().exceptionHandling().accessDeniedPage("/403.html")
			.and().formLogin().loginPage("/login.html")
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
			.and().csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(guardianDetailsService);
	    return authProvider;
	}
}
