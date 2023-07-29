package com.project.viver.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.viver.dto.test.ServerCheckResponseDto;

@FeignClient(url = "http://localhost:8080", name = "TestClient")
public interface TestClient {

    @GetMapping(value = "/health-check", consumes = "application/json")
    ServerCheckResponseDto healthCheck();

}