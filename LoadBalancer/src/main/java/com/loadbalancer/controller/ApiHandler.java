package com.loadbalancer.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;
@Slf4j
public class ApiHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	 private final ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {

		String body = event.getBody(); // JSON string
        String method = event.getHttpMethod();
		//context.getLogger().log("Method: " + event.getHttpMethod());
		//context.getLogger().log("Path: " + event.getPath());
		//context.getLogger().log("Body: " + event.getBody());
		log.info(body);
		log.info(method);
		 Map<String, Object> responseMap = new LinkedHashMap<>();
		 responseMap.put("status", "OK");
		 responseMap.put("method", event.getHttpMethod());
		 responseMap.put("bodyReceived", event.getBody());
		 String responseBody = objectMapper.writeValueAsString(responseMap);
		 return new APIGatewayProxyResponseEvent()
                 .withStatusCode(200)
                 .withHeaders(Map.of("Content-Type", "application/json"))
                 .withBody(responseBody);
	}
}
