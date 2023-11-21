package com.christmas.withchatgpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.christmas")
public class WithChatgptApplication {

	public static void main(String[] args) {
		SpringApplication.run(WithChatgptApplication.class, args);
	}

}
