package com.ak.springbootwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ak")
public class SpringBootWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebappApplication.class, args);
	}

}
