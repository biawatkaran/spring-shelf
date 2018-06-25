package com.springboot.ccs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.springboot.ccs.service")
@EnableDiscoveryClient
public class CCSApplication {

	public static void main(String[] args) {
		SpringApplication.run(CCSApplication.class, args);
	}
}
