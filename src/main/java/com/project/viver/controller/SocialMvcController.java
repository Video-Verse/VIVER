package com.project.viver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.viver.common.response.service.ResponseService;
import com.project.viver.dto.social.KakaoToken;
import com.project.viver.service.login.KakaoService;
import com.project.viver.service.login.LoginService;

import jakarta.servlet.http.HttpServletRequest;



@RestController
public class SocialMvcController {

    @Value("${kakao.client.id}")
    private String clientId;
    
    @Autowired
	private ResponseService responseService;
    
    @Autowired
    KakaoService kakaoService;
    
    @Autowired
    LoginService loginService;

//    @RequestMapping(value="/kakao.do", method = RequestMethod.GET)
//    public String login(HttpServletRequest Request, Model model) {
//        StringBuilder kakaoLoginUrl = new StringBuilder()
//                .append("https://kauth.kakao.com/oauth/authorize")
//                .append("?client_id=").append(clientId)
//                .append("&response_type=code")
//                .append("&redirect_uri=").append("http://localhost:8080/oauth/kakao/callback");
//
//        model.addAttribute("kakaoLoginUrl", kakaoLoginUrl);
//        return "Login/Login";
//    }
    
    @GetMapping("/oauth/kakao/callback")
    public String kakaoLogin(HttpServletRequest request, Model model) throws Exception {
        String code = request.getParameter("code");
        KakaoToken KakaoToken = kakaoService.getKakaoAccessToken(code);
        //loginService.kakaoLogin(KakaoToken);
        model.addAttribute("kakaoLoginUrl", loginService.kakaoLogin(KakaoToken));
        //return responseService.getSingleResult(loginService.kakaoLogin(KakaoToken));
        return "Login/Login"; 
    }
    
}
