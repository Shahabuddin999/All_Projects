package com.example.asynchronous.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClient;
@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.build();

// 		  Below comment is most important have look once
//        return builder
//                .baseUrl("http://localhost:8080/") // Default root URL
//                .build();
        
        
//         return builder.baseUrl("http://localhost:8080/api")
//                .requestInterceptor((request, body, execution) -> {
//                    request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer mock-jwt-token-123");
//                    return execution.execute(request, body);
//                })
//                .build();
// 		 	Now you don’t need to manually set .header(HttpHeaders.AUTHORIZATION, ...) in every call.
    }
}
