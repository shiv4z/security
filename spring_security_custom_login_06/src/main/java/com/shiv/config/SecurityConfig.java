package com.shiv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;
    
    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer:: disable)
				.formLogin((form) -> {
					                     form.loginPage("/login")
							                 .loginProcessingUrl("/login")
							                 .successHandler(successHandler)
							                 .failureHandler(failureHandler)
							                 .permitAll();
							                 //.defaultSuccessUrl("/about")
							                // .failureUrl("/login?error=true");
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

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    UserDetailsService getUserDetails() {
		return new CustomUserDetails();
	}

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(getUserDetails());
		return provider;
	}
}

