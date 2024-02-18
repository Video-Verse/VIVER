package com.project.viver.controller.mypage;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.viver.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
	
	private final UserService userService;
	
	/**
	 * 마이페이지 - 닉네임변경
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/nicknameChange")
	public Map<String,Object> nicknameChange(@RequestBody Map<String,Object> request) {
        return userService.nicknameChange(request);    
    }
     

}
