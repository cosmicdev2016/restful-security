package com.boot.restfulsecurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * We try to configure in-memory authentication with few users
	 * 
	 * @param auth
	 */
	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("test").password("123").roles("ADMIN")
			.and()
				.withUser("test2").password("123").roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			//.antMatchers("/posts/**").permitAll()
			.antMatchers("/posts/add").hasRole("ADMIN")
			.antMatchers("/posts/drafts").hasRole("USER")
			.antMatchers("/posts/list").permitAll() // list is open to all
			.antMatchers("/posts/**").permitAll() // rest of the urls can be open to all
			.anyRequest().authenticated() //everything should be authenticated (should be the last statement)
				.and()
			.formLogin()
				.and()
			.logout();
			
	}

}
