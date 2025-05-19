package com.retry.example.retrydemo.service;
import java.util.Map;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
 
@Service
public class RetryServiceImpl implements RetryService{
     
    Logger logger = LoggerFactory.getLogger(RetryServiceImpl.class);
     
    int counter = 1;
 
    @Override
    public String retry(Map<String, Integer> request) {
         
        logger.info("Executing retry service : "+ counter++);
        System.out.println(request.get("numerator")/request.get("denominator"));
		return "Success";
    }

	@Override
	public String getRetryFallback(Exception e, Map<String, Integer> request) {
		logger.info("Retry failed last attempt: "+ --counter+" : Exception : "+ e.getMessage());
		return "Hello from fallback method!!!";
	}
}