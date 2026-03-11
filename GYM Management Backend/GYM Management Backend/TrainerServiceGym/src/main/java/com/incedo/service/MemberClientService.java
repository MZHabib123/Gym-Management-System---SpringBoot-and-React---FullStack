package com.incedo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.incedo.dto.MemberDTO;

@Service
public class MemberClientService {

    private final WebClient webClient;

    public MemberClientService(WebClient memberWebClient) {
        this.webClient = memberWebClient;
    }

    public MemberDTO getMemberById(Long id) {
        return webClient.get()
                .uri("/members/{id}", id)
                .retrieve()
                .bodyToMono(MemberDTO.class)
                .block();
    }
}
