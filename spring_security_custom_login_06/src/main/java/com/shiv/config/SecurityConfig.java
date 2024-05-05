package com.shiv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer:: disable)
				.formLogin((form) -> {
					                     form.loginPage("/login")
							                 .loginProcessingUrl("/login")
							                 .permitAll()
							                 .defaultSuccessUrl("/about")
							                 .failureUrl("/login?error=true");
	                               } 
				)
				.authorizeHttpRequests((authorize) ->
					                               authorize
					                               .requestMatchers("/" ,"/images/**","/css/**", "/js/**", "/WEB-INF/jsp/**")
						                                       .permitAll()
						                                       .anyRequest()
						                                       .authenticated()
						                   )
				.logout((logout) ->
									logout.logoutUrl("/logout")
									      .logoutSuccessUrl("/")
									      )
				.exceptionHandling(exceptionHandling ->
											exceptionHandling
											.accessDeniedPage("/access-denied")
							)
				
				.build();
				
	}

}
