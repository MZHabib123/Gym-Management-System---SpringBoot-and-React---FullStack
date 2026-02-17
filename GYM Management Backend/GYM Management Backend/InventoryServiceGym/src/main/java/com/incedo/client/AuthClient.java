package com.incedo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "auth-service", url = "http://localhost:8081")
public interface AuthClient {

    @GetMapping("/auth/validate-token")
    Boolean validateToken(@RequestHeader("Authorization") String token);
}
