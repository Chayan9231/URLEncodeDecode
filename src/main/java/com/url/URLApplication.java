package com.url;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class URLApplication {
	public static void main(String[] args) {
		SpringApplication.run(URLApplication.class, args);
		log.info("***** Application Started ******");
	}
}
