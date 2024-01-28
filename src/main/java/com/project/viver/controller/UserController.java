package com.project.viver.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.viver.dto.user.NicknameRequest;
import com.project.viver.service.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService userService;

    @PostMapping("/SocialLogin")
    public ResponseEntity<Object> doSocialLogin(@RequestBody @Valid SocialLoginRequest request){
        return ResponseEntity.created(URI.create("/SocialLogin"))
                .body(userService.doSocialLogin(request));
    }
	
    // @PostMapping("/registerNickname")
    // public ResponseEntity<String> registerNickname(@RequestBody NicknameRequest request) {
    //     return userService.registerNickname(request);    
    // }


}
