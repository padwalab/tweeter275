package edu.sjsu.cmpe275.aop.tweet.aspect;

import java.io.IOException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.aspectj.lang.annotation.Around;

@Aspect
@Order(1)
public class RetryAspect {
	/***
	 * Following is a dummy implementation of this aspect. You are expected to
	 * provide an actual implementation based on the requirements, including
	 * adding/removing advices as needed.
	 * 
	 * @throws Throwable
	 */

	int tries = 3;

	@Around("execution(* edu.sjsu.cmpe275.aop.tweet.TweetService.*(..))")
	public void dummyAdviceOne(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("Retry ...Prior to the executuion of the metohd " + joinPoint.getSignature().getName());
		int i;
		Throwable execept = null;
		for (i = 0; i <= tries; i++) {
			try {
				joinPoint.proceed();
				break;
			} catch (IOException e) {
				execept = e;
				if (i > 0)
					System.out.println("retry tries : " + i);
				else
					System.out.println("Aborted the exeception " + joinPoint.getSignature().getName()
							+ " because of network exception");
				continue;
			}
		}
		if (i == 4 && execept != null) {
			System.out.println("Maximum retry attempts exceeded...");
			throw execept;
		}

	}
}
