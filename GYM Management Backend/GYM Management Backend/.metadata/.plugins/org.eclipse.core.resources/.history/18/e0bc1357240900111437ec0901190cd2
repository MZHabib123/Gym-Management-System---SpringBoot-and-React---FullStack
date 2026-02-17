package com.gym.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MemberClient {

    @Autowired
    private WebClient webClient;

    @Value("${member.service.url}")
    private String memberServiceUrl;

    public boolean memberExists(Long memberId) {
        try {
            webClient.get()
                    .uri(memberServiceUrl + "/" + memberId)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
