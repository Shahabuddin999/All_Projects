package com.retry.example.retrydemo.service;

import java.util.Map;
 
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
 
@Service
public interface RetryService {
 
	//@Retryable(value = { Exception.class }, maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.delay}"))
	// multiplier will makedouble delay after each try like 1000, 2000, 4000, 8000
	@Retryable(value = { Exception.class }, maxAttempts = 5,
			backoff = @Backoff(delay = 1000, multiplier = 2  /* , maxDelay = 10000 */))
    public String retry(Map<String, Integer> request);
    
    /*
     * retry()'s and getRetryFallback()'s return type and argument must be same if you are using @Recover annotation
     */
    @Recover
    public String getRetryFallback(Exception e, Map<String, Integer> request);
}