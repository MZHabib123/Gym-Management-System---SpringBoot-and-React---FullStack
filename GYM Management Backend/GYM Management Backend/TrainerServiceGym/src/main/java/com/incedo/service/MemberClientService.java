package com.incedo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.incedo.dto.MemberDTO;

@Service
public class MemberClientService {

    private final WebClient memberWebClient;

    public MemberClientService(WebClient memberWebClient) {
        this.memberWebClient = memberWebClient;
    }

    public MemberDTO getMemberById(Long memberId) {
        return memberWebClient.get()
                .uri("/{id}", memberId)
                .retrieve()
                .bodyToMono(MemberDTO.class)
                .block(); // Blocking call for simplicity; in reactive apps, use Mono<MemberDTO>
    }
}
