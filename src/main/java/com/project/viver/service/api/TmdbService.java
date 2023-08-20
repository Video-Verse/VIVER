package com.project.viver.service.api;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.viver.common.component.HttpClientComponent;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TmdbService {

    private String baseUrl = "https://api.themoviedb.org/3";

    private String language = "ko-KR";

	@Value("${api.tmdb}")
	private String apiKey;

	@Autowired
	HttpClientComponent httpClientComponent;



	 //리스트 조회
    public Map<String, Object> getDiscover() {
    	Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> header = new HashMap<String, Object>();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("api_key", apiKey);
		param.put("language", language);

		try {
			result = httpClientComponent.get(baseUrl, "/discover/movie", header, param);
			if (result == null) {
				result = new HashMap<String, Object>();
				return result;
			}
		} catch (BusinessException e) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return result;
    }

}