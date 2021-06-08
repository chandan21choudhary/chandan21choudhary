package com.java.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
	
	  @ExceptionHandler(value = UserDefinedException.class)
	   public ResponseEntity<Object> exception(UserDefinedException exception) {
	      return new ResponseEntity<>("Did not get Response", HttpStatus.INTERNAL_SERVER_ERROR);
	   }

}
