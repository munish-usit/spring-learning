package org.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// To enable web security
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	/**
	 * This method is used to configure AuthenticationManager using AuthenticationManagerBuilder
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
		.withUser("munish")
		.password("munish")
		.roles("ADMIN")
		.and()
		.withUser("bar")
		.password("bar")
		.roles("USER");
		
	}
	
	/**
	 * This is for password encoding.
	 * Spring security look for password encoder to encode the passwords
	 * @return
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	/**
	 * Authorization using HttpSecurity
	 * Order is from most restrictive to less restrictive from top to bottom
	 * 
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN") // only for admin
			.antMatchers("/user").hasAnyRole("ADMIN","USER")  // only for admin and user
			.antMatchers("/").permitAll()          // for all
			.and().formLogin();
		
	}
}
