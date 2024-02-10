package com.project.viver.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.viver.dto.user.NicknameRequest;
import com.project.viver.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
	
	private final UserService userService;
	
    @PostMapping("/join")
    public Map<String,Object> join(@RequestBody NicknameRequest request) {
        return userService.join(request);    
    }
    
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody NicknameRequest request) {
        return userService.login(request);    
    }
	
	

}
