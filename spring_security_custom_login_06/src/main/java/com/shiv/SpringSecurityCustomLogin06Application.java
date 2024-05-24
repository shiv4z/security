package com.shiv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityCustomLogin06Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringSecurityCustomLogin06Application.class, args);
		      BCryptPasswordEncoder encoder = context.getBean(BCryptPasswordEncoder.class);
		      System.out.println(encoder.encode("shiv@123"));
	}

}
