package com.project.viver.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.viver.api.TestClient;
import com.project.viver.common.constraint.Role;
import com.project.viver.dto.test.BindExceptionTestDto;
import com.project.viver.dto.test.ServerCheckResponseDto;
import com.project.viver.dto.test.TestEnum;
import com.project.viver.dto.token.JwtTokenDto;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.BusinessException;
import com.project.viver.service.common.token.TokenManagerService;

import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
	
	private final TestClient helloClient;
	private final Environment environment;
	 private final TokenManagerService tokenManagerService;
	
	@GetMapping("helloViver") 
	public List<String> hello() {
		return Arrays.asList("Hello Viver","juhui","xxohyun","eunbee","sanggooo","haeun","hyunjiii");

	}
	
//예외처리 테스트 ---------------------------------------------------------------------------
    @GetMapping("/api/exception/bind-exception-test")
    public String bindExceptionTest(@Valid BindExceptionTestDto bindExceptionTestDto) {
        return "ok";
    }
    
    @GetMapping("/api/exception/type-exception-test")
    public String typeMismatchException(TestEnum testEnum) {
        return "ok";
    }
    
    @GetMapping("/api/exception/business-exception-test")
    public String businessExceptionTest(String isError) {
        if("true".equals(isError)) {
            throw new BusinessException(ErrorCode.TEST);
        }
        return "ok";
    }

    @GetMapping("/api/exception/exception-test")
    public String exceptionTest(String isError) {
        if("true".equals(isError)) {
            throw new IllegalArgumentException("예외 테스트");
        }
        return "ok";
    }
//Feign Test ---------------------------------------------------------------------------

    @GetMapping("/health-check")
    public ResponseEntity<ServerCheckResponseDto> healthCheck() {
    	ServerCheckResponseDto healthCheckResponseDto = ServerCheckResponseDto.builder()
                .health("ok")
                .activeProfiles(Arrays.asList(environment.getActiveProfiles()))
                .build();
        return ResponseEntity.ok(healthCheckResponseDto);
    }

    
    @GetMapping("/health/feign-test")
    public ResponseEntity<ServerCheckResponseDto> healthCheckTest() {
    	ServerCheckResponseDto serverCheckResponseDto = helloClient.healthCheck();
        return ResponseEntity.ok(serverCheckResponseDto);
    }

    
  /// 
    
    @GetMapping("/api/token-test/create")
    public JwtTokenDto createJwtTokenDto() {
        return tokenManagerService.createJwtTokenDto("test", Role.ADMIN);
    }

    @GetMapping("/api/token-test/valid")
    public String validateJwtToken(@RequestParam String token) {
    	tokenManagerService.validateToken(token);
        Claims tokenClaims = tokenManagerService.getTokenClaims(token);
        String userId = (String) tokenClaims.get("userId");
        String role = (String) tokenClaims.get("role");
        log.info("userId : {}", userId);
        log.info("role : {}", role);
        return "success";
    }
}
