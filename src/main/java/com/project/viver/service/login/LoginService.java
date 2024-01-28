package com.project.viver.service.login;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.viver.common.constraint.CommonId;
import com.project.viver.common.constraint.Role;
import com.project.viver.common.constraint.UserType;
import com.project.viver.dto.social.KakaoProfile;
import com.project.viver.dto.social.KakaoToken;
import com.project.viver.entity.user.User;
import com.project.viver.service.common.CommonService;
import com.project.viver.service.user.UserService;

@Service
public class LoginService {
	
	@Autowired
	KakaoService kakaoService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommonService commonService;

	public Map<String,Object> kakaoLogin (KakaoToken KakaoToken) throws Exception {
		Map<String,Object> result = new HashMap<>();
		
		User oauthUser =  null;
		
		//토큰으로 프로필 가져오기
		KakaoProfile profile = kakaoService.getKakaoProfile(KakaoToken.getAccess_token());
		String kakaoEmail = profile.getKakao_account().getEmail();
		
		//db에서 비교하기
		Optional<User> user = userService.findByKakao(kakaoEmail);

		if(user.isEmpty()) { // 신규 회원 가입
			
            oauthUser =  User.builder()
                    .userType(UserType.KAKAO)
                    .kakao(kakaoEmail)
                    .userId(commonService.getId(CommonId.USER.value()))
                    .role(Role.USER)
                    .kakaoId(String.valueOf(profile.getId()))
                    .build();
            
            oauthUser = userService.registerUser(oauthUser);
            
        } else { //기존 회원 
        	
        	oauthUser = user.get();
        }
		
		result.put("user", oauthUser);
		
		return result;
	}
}
