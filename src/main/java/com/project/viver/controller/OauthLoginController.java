package com.project.viver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.viver.common.constraint.UserType;
import com.project.viver.common.util.AuthorizationHeaderUtils;
import com.project.viver.dto.oauth.OauthLoginDto;
import com.project.viver.service.common.oauth.LogoutService;
import com.project.viver.service.common.oauth.OauthLoginService;
import com.project.viver.service.common.oauth.OauthValidator;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OauthLoginController {

	private final OauthValidator oauthValidator;
	private final OauthLoginService oauthLoginService;
	private final LogoutService logoutService;

	@PostMapping("/login")
	public ResponseEntity<OauthLoginDto.Response> oauthLogin(@RequestBody OauthLoginDto.Request oauthLoginRequestDto,
			HttpServletRequest httpServletRequest) {

		String authorizationHeader = httpServletRequest.getHeader("Authorization");
		AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);
		oauthValidator.validateUserType(oauthLoginRequestDto.getUserType());

		String accessToken = authorizationHeader.split(" ")[1];
		OauthLoginDto.Response jwtTokenResponseDto = oauthLoginService.oauthLogin(accessToken,
				UserType.from(oauthLoginRequestDto.getUserType()));
		return ResponseEntity.ok(jwtTokenResponseDto);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest httpServletRequest) {
		String authorizationHeader = httpServletRequest.getHeader("Authorization");
		AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);

		String accessToken = authorizationHeader.split(" ")[1];
		logoutService.logout(accessToken);

		return ResponseEntity.ok("logout success");
	}

}