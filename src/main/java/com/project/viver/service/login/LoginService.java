package com.project.viver.service.login;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.viver.dto.social.KakaoProfile;
import com.project.viver.dto.social.KakaoToken;
import com.project.viver.entity.user.User;
import com.project.viver.service.user.UserService;

@Service
public class LoginService {
	
	@Autowired
	KakaoService kakaoService;
	
	@Autowired
	UserService userService;

	public Map<String,Object> kakaoLogin (KakaoToken KakaoToken) throws Exception {
		//토큰으로 프로필 가져오기
		KakaoProfile profile = kakaoService.getKakaoProfile(KakaoToken.getAccess_token());
		String kakaoEmail = profile.getKakao_account().getEmail();
		
		//db에서 비교하기
		Optional<User> user = userService.findByKakao(kakaoEmail);
		
		//신규
		
		//기존 회원
		
		
		return null;
	}
}
