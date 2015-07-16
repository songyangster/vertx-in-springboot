package com.test.vertx.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AroundAspect {

	@Around("execution(* com.test.vertx..*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
		Signature signature = proceedingJoinPoint.getSignature();

		long startTime = System.currentTimeMillis();
		System.out.println("Before invoking method =============== " + signature + " startTime=" + startTime);

		Object value = null;

		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		System.out.println("After invoking method ----------------- " + signature + "\n" + value);
		System.out.println("Time to execute =============== " + signature + " = " + (System.currentTimeMillis() - startTime) + "ms");

		return value;
	}
}
