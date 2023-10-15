package com.project.viver.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.viver.service.api.MusicalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApiService {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	MusicalService kopisService;

	/**
	 * 검색
	 * 
	 * @param params
	 * @return
	 * @throws ParseException
	 */
	public List<Map<String, Object>> search(Map<String, Object> params) throws ParseException {
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String,Object> musical = new HashMap<>();
		// 일배치를 돌린다면 db에서만 가져와도 되지 않을까 하는 맴
		// 초기 데이터를 많이 넣어놔야 할듯

		logger.debug("search start");
		// 1. db에 먼저 찾아보기
		// 뮤지컬
		List<Map<String, Object>> musicalList = kopisService.getList(params);
		musical.put("musical", musicalList);
		result.add(musical);

		// 영화
		// tmdb 이미지 base url https://image.tmdb.org/t/p/original
		//

		// 2. 결과가 적으면 api 날려보기
		//kopisService.sendApi(params);
		// 3. 그 결과 디비에 업데이트 치기

		// keyword
		// movie

		// drama

		// musical
		//
		// 일배치 통해 db에 들어간다 -> db 확인해서 뿌려주기
		return result;
	}

}
