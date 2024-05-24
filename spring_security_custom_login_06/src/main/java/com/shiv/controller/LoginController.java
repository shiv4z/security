package com.shiv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.shiv.config.CustomUser;
import com.shiv.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}
	
	@GetMapping("/about")
	public String about(HttpSession session) {
		CustomUser attribute =(CustomUser) session.getAttribute("user");
		System.out.println(attribute.getUsername());
		return "about";
	}

}
