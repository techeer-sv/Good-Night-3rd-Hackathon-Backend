package com.example.goodnight3rdhackathonbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GoodNight3rdHackathonBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodNight3rdHackathonBackendApplication.class, args);
	}

}
