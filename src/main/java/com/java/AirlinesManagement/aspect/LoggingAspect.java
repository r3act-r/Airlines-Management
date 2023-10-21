package com.java.AirlinesManagement.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger logger = LogManager.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* com.java.AirlinesManagement.service.*.*(..))")
	private void serviceMethods() {
		
	}
	
	@AfterReturning(pointcut="serviceMethods()",returning="result")
	public void logServiceMethods(JoinPoint joinPoint ,Object result) {
		
		logger.info("Method executed: {}.{} with result : {}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(),result);
	}
}
