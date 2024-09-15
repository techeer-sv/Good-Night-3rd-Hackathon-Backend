package com.example.techeertree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TecheerTreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TecheerTreeApplication.class, args);
	}

}
