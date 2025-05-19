package com.actuator.overrideactuator;

import java.util.Random;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator extends AbstractHealthIndicator{

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		//Overriding health actuator : http://localhost:8088/olxapp/health  method: get 
		Random random = new Random();
		if(random.nextInt()%2==0)
			builder.up();
		else
			builder.down();
	}

}
