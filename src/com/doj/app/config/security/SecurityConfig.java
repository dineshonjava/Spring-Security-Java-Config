/**
 * 
 */
package com.doj.app.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author Dinesh.Rajput
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired DataSource dataSource;
	
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password("password").roles("USER").build());
		return manager;
	}
	//1. Default
	//Default configuration in WebSecurityConfigurerAdapter
	/*protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.and()
			.httpBasic();
	}*/
	
	//2. Custom Login Form
	//Override Default configuration in WebSecurityConfigurerAdapter for custom login form instead auto generated login form by spring security
	//The updated configuration specifies the location of the log in page.
	//We must grant all users (i.e. unauthenticated users) access to our log in page
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login") 
				.permitAll();        
	}*/
	
	//3. Customization to authorize request
	//Override Default configuration in WebSecurityConfigurerAdapter for custom login form and authorize requests
	//We specified multiple URL patterns that any user can access like "/resources/", "/scripts/", "/css/" etc.
	//Any URL that starts with "/admin/" will be restricted to users who have the role "ROLE_ADMIN". 
	//Any URL that starts with "/db/" requires the user to have both "ROLE_ADMIN" and "ROLE_DBA". 
	//Any URL that has not already been matched on only requires that the user be authenticated
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()                                                                
				.antMatchers("/resources/**", "/signup", "/about").permitAll()                  
				.antMatchers("/admin/**").hasRole("ADMIN")                                      
				.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")            
				.anyRequest().authenticated()                                                   
				.and()
			.formLogin()
				.loginPage("/login") 
				.permitAll();
	}
	//In memory authentication java configuration
	//Not web-specific
	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication() //Adds a UserDetailsManagerConfigurer
				//login, password and supported role
				.withUser("user").password("password").roles("USER").and()
				.withUser("admin").password("adminpassword").roles("ADMIN").and()
				.withUser("dinesh").password("support").roles("SUPPORT");
	}*/
	
	//JDBC Authentication
	//Provides default queries
	//– SELECT username, password, enabled FROM users WHERE username = ?
	//– SELECT username, authority FROM authorities WHERE username = ?
	//We can customize the default queries by using following methods
	//usersByUsernameQuery()
	//authoritiesByUsernameQuery()
	//groupAuthoritiesByUsername()
	/*public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
			.usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
			.authoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?")
			.dataSource(dataSource);
	}*/
}
