package com.lpu.todo.security;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	CustomAuthenticationFailureHandler failureHandler;
	@Autowired
	CustomAuthenticationSuccessHandler successHandler;
	
	@Autowired
	CustomLogoutSuccessHandler logoutHandler;
	
	@Bean
	JdbcUserDetailsManager users() {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//		jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, enabled from users where username=?");
//		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username, authrity from authorities where username=?"));
		return jdbcUserDetailsManager;
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(AbstractHttpConfigurer::disable)
		.cors(cors -> cors.configurationSource(corsConfigurationSource()) )	
		.authorizeHttpRequests(auth ->
		auth
		.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
		.requestMatchers("/", "/user", "/register", "/login", "/api/current-user").permitAll()
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
		.anyRequest().authenticated()	) 

		.userDetailsService(users())
		.formLogin(
				form -> form.loginPage("/login").permitAll()
				.successHandler(successHandler)
//				.defaultSuccessUrl("/")
				.failureHandler(failureHandler)
				)
				.logout(logout -> logout.permitAll()
						.logoutSuccessHandler(logoutHandler)
						);
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5500", "https://your-frontend-domain.com", "*")); // Allowed origins
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed methods
		configuration.setAllowedHeaders(Arrays.asList("*")); // Allowed headers
		configuration.setAllowCredentials(true); // Allow sending credentials (cookies, HTTP authentication)
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/", configuration); // Apply CORS to all paths
		return source;
	}

}

