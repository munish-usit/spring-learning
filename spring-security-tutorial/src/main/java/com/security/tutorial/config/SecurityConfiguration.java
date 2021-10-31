package com.security.tutorial.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.tutorial.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource datasource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		System.out.println("authentication manager builder configuration");

		// in-memory authentication
		/**
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("user")
			.roles("USER")
			.and()
			.withUser("admin")
			.password("admin")
			.roles("ADMIN");
		 **/ 
		

		// jdbc authentication using h2 database
		/**
		auth.jdbcAuthentication()
		.dataSource(datasource)
		.withDefaultSchema()
		.withUser(User.builder().username("user_h2").password("user_h2").roles("USER"))
		.withUser(User.withUsername("admin_h2").password("admin_h2").roles("ADMIN"));
		 **/ 

		// jdbc authentication using postgres and schema which is same as default schema
		/**
		auth.jdbcAuthentication()
		.dataSource(datasource);
		 **/ 
	
		
		// jdbc authentication using postgres and custom schema
		/**
		 auth.jdbcAuthentication()
	      .dataSource(datasource)
	      .usersByUsernameQuery("select email,password,enabled "
	        + "from user_profile "
	        + "where email = ?")
	      .authoritiesByUsernameQuery("select email,authority "
	        + "from user_authorities "
	        + "where email = ?");
	      **/
		
		// jpa based authenticaiton
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();

	}

	/**
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("authorization configuration");
		http.authorizeRequests()
		.antMatchers("/v1/admin").hasRole("ADMIN")
		.antMatchers("/v1/user").hasAnyRole("USER","ADMIN")
		.anyRequest().permitAll()
		.and()
		.httpBasic()
		.and()
		.formLogin();
	}
	**/
	
	// configuration for jwt based login flow
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		System.out.println("authorization configuration");
		httpSecurity.csrf().disable()
		// dont authenticate this particular request
		.authorizeRequests().antMatchers("/v1/login").permitAll().
		// all other requests need to be authenticated
		anyRequest().authenticated().and().
		// make sure we use stateless session; session won't be used to store user's state.
		exceptionHandling().and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Add a filter to validate the token for every request before spring security UsernamePasswordAuthenticationFilter
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	// authentication manager bean required in login service 
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
