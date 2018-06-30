package com.springboot.ccs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.springboot.ccs.service")
@EnableCircuitBreaker
@SpringBootApplication
public class CCSApplication {

	public static void main(String[] args) {
		SpringApplication.run(CCSApplication.class, args);
	}
}
