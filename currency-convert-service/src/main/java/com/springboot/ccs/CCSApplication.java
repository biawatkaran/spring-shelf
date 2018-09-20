package com.springboot.ccs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableFeignClients("com.springboot.ccs.service")
@EnableCircuitBreaker
@EnableResourceServer
@SpringBootApplication
public class CCSApplication {

	public static void main(String[] args) {
		SpringApplication.run(CCSApplication.class, args);
	}
}
