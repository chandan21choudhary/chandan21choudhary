package com.java.controller;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.java.Exception.UserDefinedException;
import com.java.model.Employee;

@Controller
@RestController
public class EmployeeConsumerController {

	@RequestMapping(value="/getEmp",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Employee[]> consumeEmployee() {

		String baseUrl = "http://localhost:9999/getEmployee";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Employee[]> response=null;
		try {
			//response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), Employee.class);
			response = restTemplate.getForEntity(baseUrl, Employee[].class);
		} catch (Exception e) {
			if (response == null) {
				throw new UserDefinedException();
			}
		}
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());
		return response;
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

}
