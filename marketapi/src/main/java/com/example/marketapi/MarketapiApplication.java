package com.example.marketapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MarketapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketapiApplication.class, args);
	}

}
