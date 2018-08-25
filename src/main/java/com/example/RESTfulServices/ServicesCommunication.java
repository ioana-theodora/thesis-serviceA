package com.example.RESTfulServices;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ServicesCommunication {

	private static final Logger log = LoggerFactory.getLogger(Application.class);


	public static void main(String[] args) {

		SpringApplication.run(ServicesCommunication.class, args);
	}
}
