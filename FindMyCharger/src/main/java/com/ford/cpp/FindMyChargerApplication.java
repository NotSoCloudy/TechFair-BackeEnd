package com.ford.cpp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FindMyChargerApplication {

	public static void main(String[] args) {
		
		System.out.println("THIS HAS BEEN INVOKED");
		SpringApplication.run(FindMyChargerApplication.class, args);
	}

}
