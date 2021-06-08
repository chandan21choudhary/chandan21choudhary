package com.java.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.java.Exception.UserDefinedException;
import com.java.model.Employee;

@RestController
public class EmployeeConsumerControllerDiscovery {
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private LoadBalancerClient loadBalancer;

	@RequestMapping(value="/getEmployee",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee[]> consumeEmployee() {
		/*
		 * List<ServiceInstance> instances =
		 * discoveryClient.getInstances("employee-producer"); ServiceInstance
		 * serviceInstance = instances.get(0); String baseUrl =
		 * serviceInstance.getUri().toString();
		 */
		ServiceInstance serviceInstance=loadBalancer.choose("employee-producer");
		System.out.println(serviceInstance.getUri());
		String baseUrl=serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/getEmployee";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Employee[]> response = null;
		try {
			//response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), Employee[].class);
			 response = restTemplate.getForEntity(baseUrl, Employee[].class);
			//Object[] objects = responseEntity.getBody();
		} catch (Exception e) {
			if (response == null) {
				throw new UserDefinedException();
			}
		}
		Employee[] list=response.getBody();
		for(Employee emp:list) {
			System.out.println(emp);
		}
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());
		return response;
	}

	@SuppressWarnings("unused")
	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
}
}
