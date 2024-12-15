package com.webgame.webgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class WebGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebGameApplication.class, args);
	}

}
