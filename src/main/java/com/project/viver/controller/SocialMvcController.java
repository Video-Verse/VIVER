package com.project.viver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;



@Controller
@RequestMapping(value = "Login")
public class SocialMvcController {

    @Value("${kakao.client.id}")
    private String clientId;

    @RequestMapping(value="/login.do", method = RequestMethod.GET)
    public String login(HttpServletRequest Request, Model model) {
        StringBuilder kakaoLoginUrl = new StringBuilder()
                .append("https://kauth.kakao.com/oauth/authorize")
                .append("?client_id=").append(clientId)
                .append("&response_type=code")
                .append("&redirect_uri=").append("http://localhost:8080/oauth/kakao/callback");

        model.addAttribute("kakaoLoginUrl", kakaoLoginUrl);
        return "Login/Login";
    }
    
}
