package com.project.viver.service.login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.project.viver.dto.social.KakaoProfile;
import com.project.viver.dto.social.KakaoToken;

@Service
public class KakaoService {

	@Value("${spring.url.base}")
	private String baseUrl;
	
	@Value("${spring.url.front}")
	private String frontUrl;

	@Value("${kakao.url.profile}")
	private String kakaoUrlProfile;

	@Value("${kakao.url.token}")
	private String kakaoUrlToken;

	@Value("${kakao.client.id}")
	private String kakaoClientId;

	@Value("${kakao.client.secret}")
	private String kakaoClientSecret;

	@Value("${kakao.url.redirect}")
	private String kakaoRedirect;

	public KakaoProfile getKakaoProfile(String accessToken) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();
		// Set header : Content-type: application/x-www-form-urlencoded
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", "Bearer " + accessToken);

		// Set http entity
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
		try {
			// Request profile
			ResponseEntity<String> response = restTemplate.postForEntity(kakaoUrlProfile, request, String.class);
			if (response.getStatusCode() == HttpStatus.OK)
				return gson.fromJson(response.getBody(), KakaoProfile.class);
		} catch (Exception e) {
			throw new Exception();
		}
		throw new Exception();
	}

	public KakaoToken getKakaoAccessToken(String code) {
		RestTemplate restTemplate = new RestTemplate();
		Gson gson = new Gson();
		// Set header : Content-type: application/x-www-form-urlencoded
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		// Set parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_secret", kakaoClientSecret);
		params.add("client_id", kakaoClientId);
		params.add("redirect_uri", baseUrl + kakaoRedirect);
		params.add("code", code);
		// Set http entity
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

		ResponseEntity<String> response = restTemplate.exchange(kakaoUrlToken, HttpMethod.POST, request, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			return gson.fromJson(response.getBody(), KakaoToken.class);
		}
		return null;
	}
}
