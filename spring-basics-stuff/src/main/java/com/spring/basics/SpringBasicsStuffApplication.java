package com.spring.basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBasicsStuffApplication {

	public static void main(String[] args) {

		int[] input = {1, 2, 3, 4};
		int key = 6;

		/*
		// Without Spring
		BubbleSortAlgorithmImpl bubbleAlgo = new BubbleSortAlgorithmImpl();
		Finder sortNumbers = new Finder(bubbleAlgo);
		System.out.println(sortNumbers.find(input, key));
		*/

		ApplicationContext applicationContext = SpringApplication.run(SpringBasicsStuffApplication.class, args);

		System.out.println("======================================");
		System.out.println("Beans");
		List<String> beanDefNames = Arrays.asList(applicationContext.getBeanDefinitionNames());
		beanDefNames.forEach(System.out :: println);
		System.out.println("======================================");

		// With Spring
		Finder finder = applicationContext.getBean(Finder.class);
		int searchResult = finder.find(input, key);
		System.out.println(searchResult);
	}
}
