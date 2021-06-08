package com.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeeConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeConsumerApplication.class, args);
	}

}
