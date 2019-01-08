package com.soumyadeep.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.soumyadeep.microservices.controller.ConsumerControllerClient;

@SpringBootApplication
public class EmployeeConsumerApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(EmployeeConsumerApplication.class, args);
		
		ConsumerControllerClient consumerControllerClient=ctx.getBean(ConsumerControllerClient.class);
		
		System.out.println(consumerControllerClient);
		
		consumerControllerClient.getEmployee();
	}
	
	@Bean
	public ConsumerControllerClient consumerControllerClient() {
		
		return new ConsumerControllerClient();
	}

}

