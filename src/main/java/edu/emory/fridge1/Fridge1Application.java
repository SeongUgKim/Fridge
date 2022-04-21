package edu.emory.fridge1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
public class Fridge1Application {

	public static void main(String[] args) {
		SpringApplication.run(Fridge1Application.class, args);
	}

}
