package com.soumyadeep.microservices.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class ConsumerControllerClient {

	@Autowired
	private DiscoveryClient discoveryClient;

	public void getEmployee() {

		// String baseUrl="http://localhost:8092/employee";
		
		List<ServiceInstance> instances = discoveryClient.getInstances("employee-producer");
		
		ServiceInstance serviceInstance = instances.get(0);
		
		System.out.println(serviceInstance.getUri());
		
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/employee";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println(response.getBody());
		System.out.println(response.getStatusCodeValue());
	}

	private static HttpEntity<?> getHeaders() throws IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}
