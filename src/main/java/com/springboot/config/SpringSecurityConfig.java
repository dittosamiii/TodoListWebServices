package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

	@SuppressWarnings("unused")
	private UserDetailsService userDetailsService;

	public SpringSecurityConfig(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}

	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorize -> {
//			authorize.requestMatchers(HttpMethod.POST, "api/**").hasRole("ADMIN");
//			authorize.requestMatchers(HttpMethod.PUT, "api/**").hasRole("ADMIN");
//			authorize.requestMatchers(HttpMethod.DELETE, "api/**").hasRole("ADMIN");
//			authorize.requestMatchers(HttpMethod.GET, "api/**").hasAnyRole("ADMIN", "USER");
//			authorize.requestMatchers(HttpMethod.PATCH, "api/**").hasAnyRole("ADMIN", "USER");
//			authorize.requestMatchers(HttpMethod.GET, "api/**").permitAll();
			authorize.anyRequest().authenticated();
		}).httpBasic(Customizer.withDefaults());
		return http.build();
	}

//	@Bean
//	UserDetailsService userDetailsService() {
//		UserDetails sam = User.builder().username("sam").password(passwordEncoder().encode("ditto")).roles("USER")
//				.build();
//		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(sam, admin);
//	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
