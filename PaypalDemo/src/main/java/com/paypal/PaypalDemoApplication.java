package com.paypal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.paypal")
public class PaypalDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaypalDemoApplication.class, args);
    }
}
