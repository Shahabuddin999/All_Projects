package com.retry.CircuitBreakerAndRetry.controller;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retry.CircuitBreakerAndRetry.service.IpCounterService;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.resilience4j.ratelimiter.RateLimiter;

@RestController
public class ExternalServiceController {

	private final Random random = new Random();
	@Autowired
	private CircuitBreakerRegistry registry;
	private static final Logger log = LoggerFactory.getLogger(ExternalServiceController.class);
	@Autowired
	private RateLimiterRegistry rateLimiterRegistry;
	 @Autowired
	 private IpCounterService ipCounterService;
	@GetMapping("/api/call-external")
	@Retry(name = "externalService", fallbackMethod = "retryExhaustedFallback") // <--- Add fallbackMethod to @Retry
	@CircuitBreaker(name = "externalService", fallbackMethod = "circuitBreakerOpenFallback") // <--- Rename CB fallback for clarity
	public String callExternalService() {
		if (random.nextInt(5) < 5) { // 50% chance to fail
			log.error("💥 Simulating failure");
			throw new RuntimeException("💥 Simulated service failure");
		}
		return "✅ Success: External service responded! " + registry.circuitBreaker("externalService").getState().name();
	}

	// This fallback is specifically for when the Circuit Breaker is OPEN
	// and blocks the call from even going to the main method or retry.
	public String circuitBreakerOpenFallback(CallNotPermittedException ex) { // <--- Renamed method
		log.info("In sercuit breaker!");
		return "Error from Circuit Breaker: Circuit is OPEN! " + ex.getMessage() + " , "
				+ registry.circuitBreaker("externalService").getState().name();
	}

	// This fallback is specifically for when all @Retry attempts have failed.
	// It should have the same return type as the main method and can take the
	// exception as a parameter.
	public String retryExhaustedFallback(Exception ex) { // <--- New method for Retry's fallback
		log.warn("🚨 RETRY EXHAUSTED FALLBACK ACTIVATED! Circuit Breaker State: {} due to: {}",
				registry.circuitBreaker("externalService").getState(), ex.getMessage());
		return "Error from Retry Fallback: All retries failed! " + ex.getMessage() + " , "
				+ registry.circuitBreaker("externalService").getState().name();
	}

	@GetMapping("/limited-api")
	public String limitedEndpoint(HttpServletRequest request) {
		String clientIp = getClientIp(request);

		RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter(clientIp);
		int count = ipCounterService.incrementAndGet(clientIp);
       
       
		if (rateLimiter.acquirePermission()) {
			System.out.println( "IP: " + clientIp + " has made " + count + " requests.");
			return "Hello from IP: " + clientIp;
		} else {
			return "Rate limit exceeded for IP: " + clientIp;
		}
	}

	private String getClientIp(HttpServletRequest request) {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}
}