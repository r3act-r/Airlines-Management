package com.java.AirlinesManagement.aspect;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class RequestLoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(RequestLoggingAspect.class);
	
	@Before("execution(* com.java.AirlinesManagement.controller.*.*(..))")
	public void logRequest(JoinPoint joinPoint) {
		
		ServletRequestAttributes requestAttribute =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		
		HttpServletRequest request = requestAttribute.getRequest();
		
		logger.info("Request : {} {}",request.getMethod(),request.getRequestURI());
		logger.info("IP Address : {}",request.getRemoteAddr());
		logger.info("Parameters {}",Arrays.toString(joinPoint.getArgs()));
	}
			
}
