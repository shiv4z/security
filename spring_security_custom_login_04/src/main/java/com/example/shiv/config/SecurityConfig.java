package com.example.shiv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		  http.csrf().disable()
          .authorizeHttpRequests((authorize) ->
                  authorize.anyRequest().authenticated()
          ).formLogin(
                  form -> form
                          .loginPage("/login")
                          .loginProcessingUrl("/login")
                          .defaultSuccessUrl("/home")
                          .permitAll()
          ).logout(
                  logout -> logout
                          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                          .permitAll()
          );
  return http.build();
	}

}
