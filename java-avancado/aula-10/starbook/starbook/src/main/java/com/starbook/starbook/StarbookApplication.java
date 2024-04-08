package com.starbook.starbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class StarbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarbookApplication.class, args);
		log.info("Application started...");
	}

}
