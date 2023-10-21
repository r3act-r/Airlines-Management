package com.java.AirlinesManagement.exceptions;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@Component
@RestControllerAdvice
public class GlobalExceptionHandler  {
	
	@ExceptionHandler(RouteException.class)
	public ResponseEntity<ErrorInfo> routeNotFoundException(RouteException ex)
	{
		ErrorInfo err = new ErrorInfo();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setTimeStamp(new Date().toString());
		
		return new ResponseEntity<ErrorInfo>(err,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(AirlineException.class)
	public ResponseEntity<ErrorInfo> airlineNotFoundException(AirlineException ex)
	{
		ErrorInfo err = new ErrorInfo();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setTimeStamp(new Date().toString());
		
		return new ResponseEntity<ErrorInfo>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> methodArgumentNotValidException(MethodArgumentNotValidException e)
	{
		String message= e.getBindingResult().getAllErrors().stream().map(ObjectError :: getDefaultMessage).collect(Collectors.joining(","));
		
		ErrorInfo err = new ErrorInfo();
		err.setMessage(message);
		err.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		err.setTimeStamp(new Date().toString());
		
		return new ResponseEntity<ErrorInfo>(err,HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorInfo> constraintViolationException(ConstraintViolationException e)
	{
		String message= e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
		
		ErrorInfo err = new ErrorInfo();
		err.setMessage(message);
		err.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		err.setTimeStamp(new Date().toString());		
		return new ResponseEntity<ErrorInfo>(err,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo>generalExceptionHandler(Exception e)
	{
		ErrorInfo err = new ErrorInfo();
		err.setMessage(e.getMessage());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setTimeStamp(new Date().toString());
		
		return new ResponseEntity<ErrorInfo>(err,HttpStatus.BAD_GATEWAY);
	}
	
	
	
}
