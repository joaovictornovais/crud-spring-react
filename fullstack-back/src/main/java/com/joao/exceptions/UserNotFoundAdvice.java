package com.joao.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotFoundAdvice {
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	public Map<String, String> exceptionHandler(UserNotFoundException exception) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", exception.getMessage());
		
		return errorMap;
	}

}
