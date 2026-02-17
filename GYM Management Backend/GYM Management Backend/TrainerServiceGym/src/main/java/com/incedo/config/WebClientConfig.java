package com.incedo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient memberWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8081/members/{id}") // Member Service base URL
                .build();
    }
}
