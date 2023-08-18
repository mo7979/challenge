package com.example.challenge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	  protected void configure(HttpSecurity http) throws Exception {

	  
	     http.authorizeRequests()
	          .antMatchers("/login")
	              .permitAll()
	          .antMatchers("/**")
	              .hasAnyRole("BASICO")
	          .and()
	              .formLogin()
	              .loginPage("/login")
	              .defaultSuccessUrl("/dashboard")
	              .failureUrl("/login?error=true")
	              .permitAll()
	          .and()
	              .logout()
	              .logoutSuccessUrl("/login?logout=true")
	              .invalidateHttpSession(true)
	              .permitAll();
	     
	              http.authorizeRequests()
	              .antMatchers("/login")
	                  .permitAll()
	              .antMatchers("/**")
	                  .hasAnyRole("BASICO")
	                  .and()
	                  .formLogin()
	                  .loginPage("/login")
	                  .defaultSuccessUrl("/dashboard")
	                  .failureUrl("/login?error=true")
	                  .permitAll()
	              .and()
	                  .logout()
	                  .logoutSuccessUrl("/login?logout=true")
	                  .invalidateHttpSession(true)
	                  .permitAll()
	                  .and()
	                  .csrf()
	                  .disable();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("BASICO");
	}
    
}