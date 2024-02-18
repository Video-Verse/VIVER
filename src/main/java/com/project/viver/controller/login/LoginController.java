package com.project.viver.controller.login;

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
	
    /**
     * 회원가입
     * 
     * @param request
     * @return
     */
    @PostMapping("/join")
    public Map<String,Object> join(@RequestBody NicknameRequest request) {
        return userService.join(request);    
    }
    
    /**
     * 로그인
     * 
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody NicknameRequest request) {
        return userService.login(request);    
    }
	
	

}
