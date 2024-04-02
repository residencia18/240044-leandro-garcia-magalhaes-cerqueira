package com.example.usercrud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsercrudApplication{
	
	public static final Logger log = LoggerFactory.getLogger(UsercrudApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(UsercrudApplication.class, args);
	}
	


}
