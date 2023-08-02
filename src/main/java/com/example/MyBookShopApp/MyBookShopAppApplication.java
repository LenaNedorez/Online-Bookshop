package com.example.MyBookShopApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;

@SpringBootApplication
public class MyBookShopAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBookShopAppApplication.class, args);
	}

}
