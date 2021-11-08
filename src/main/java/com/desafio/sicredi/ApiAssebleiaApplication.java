package com.desafio.sicredi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class ApiAssebleiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAssebleiaApplication.class, args);
	}

}
