package com.growingitskill.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final String USERNAME_QUERY = "SELECT login_id, login_password, enabled FROM member WHERE login_id = ?";
	private final String AUTHORITIES_BY_USERNAME_QUERY = "SELECT login_id, role FROM member WHERE  login_id = ?";
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").and().authorizeRequests().antMatchers("/admin/**").authenticated().and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(USERNAME_QUERY).authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY).passwordEncoder(new BCryptPasswordEncoder());
	}
}