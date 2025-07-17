package com.paypal.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.dto.PayPalProps;

@Configuration
@EnableConfigurationProperties(PayPalProps.class)
public class PayPalConfig {

    @Bean
    public PayPalEnvironment environment(PayPalProps props) {
        return "sandbox".equalsIgnoreCase(props.getMode())
                ? new PayPalEnvironment.Live(props.getClientId(), props.getSecret())
                : new PayPalEnvironment.Sandbox(props.getClientId(), props.getSecret());
    }

    @Bean
    public PayPalHttpClient payPalClient(PayPalEnvironment env) {
        return new PayPalHttpClient(env);
    }
}
