package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.sun.istack.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger myLogger = Logger.getLogger(CRMLoggingAspect.class);

	// setup point cut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	public void forControllerpackage() {
	}

	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	public void forServicepackage() {
	}

	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	public void forDaopackage() {
	}

	@Pointcut("forDaopackage() || forServicepackage() || forControllerpackage()")
	private void forAppFlow() {
	}

	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		// display method we are calling
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("The @before method called --> " + method);

		// display the arguments to the method

		// get the arguments
		Object[] args = theJoinPoint.getArgs();

		// loop thru and display the arguments
		for (Object tempArg : args) {
			myLogger.info("===>>> Argument -->" + tempArg);
		}

	}

	// add @AfterReturning Advice
	@AfterReturning(pointcut="forAppFlow()",returning="theResult")
	public void AterReturning(JoinPoint theJoinPoint, Object theResult) {

		// display method we are calling
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("The @AfterReturning method called --> " + method);
		
		//display the data returned 
		myLogger.info("====>>> result: "+ theResult);
	}
}
