package com.ssw.nosleep4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Nosleep4Application {

	public static void main(String[] args) {
		SpringApplication.run(Nosleep4Application.class, args);
	}

}
