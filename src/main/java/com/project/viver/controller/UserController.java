package com.project.viver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.viver.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;

//    @PostMapping("/SocialLogin")
//    public ResponseEntity<Object> doSocialLogin(@RequestBody @Valid SocialLoginRequest request){
//        return ResponseEntity.created(URI.create("/SocialLogin"))
//                .body(userService.doSocialLogin(request));
//    }
	
//     @PostMapping("/registerNickname")
//     public ResponseEntity<String> registerNickname(@RequestBody NicknameRequest request) {
//         return userService.registerNickname(request);    
//     }
     

     


}
