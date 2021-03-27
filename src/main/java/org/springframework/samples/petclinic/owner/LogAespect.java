package org.springframework.samples.petclinic.owner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LogAespect {

	Logger logger = (Logger) LoggerFactory.getLogger(LogAespect.class);

	@Around("@annotation(LogExecutionTime)")

	//joinPoint ? @LogExecutionTime 어노테이션이 붙은 메소드
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object proceed = joinPoint.proceed();

		stopWatch.stop();
		logger.info(stopWatch.prettyPrint());

		return proceed;
	}
}
