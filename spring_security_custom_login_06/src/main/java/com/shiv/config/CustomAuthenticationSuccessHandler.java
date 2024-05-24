package com.shiv.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();
		System.out.println("Authentication Credentials ::"+authentication.getCredentials());
		System.out.println("Authentication Details ::"+authentication.getDetails());
		System.out.println("Authentication Principles :: "+authentication.getPrincipal());
		System.out.println("name :: "+authentication.getName());
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for(GrantedAuthority q: authorities) {
			System.out.println("Authority :: "+q);
		}
		session.setAttribute("user", (CustomUser)authentication.getPrincipal());
		response.sendRedirect("/about");
	}

}
