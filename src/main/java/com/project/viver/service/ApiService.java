package com.project.viver.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.viver.common.constraint.CommonId;
import com.project.viver.service.api.MovieService;
import com.project.viver.service.api.MusicalService;
import com.project.viver.service.api.TvService;
import com.project.viver.service.user.BookmarkService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApiService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	final String tmdbImgBaseurl = "https://image.tmdb.org/t/p/original";
	@Autowired
	MusicalService kopisService;

	@Autowired
	MovieService movieService;

	@Autowired
	TvService tvService;
	
	@Autowired
	BookmarkService bookmarkService;

	/**
	 * 검색
	 * 
	 * @param params
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> search(Map<String, Object> params) throws ParseException {
		logger.debug("검색 시작");
		Map<String, Object> result = new HashMap<>();

		List<Map<String, Object>> bookmarkList = new ArrayList<>();
		List<Map<String, Object>> movieList = new ArrayList<>();
		List<Map<String, Object>> tvList = new ArrayList<>();
		List<Map<String, Object>> musicalList = new ArrayList<>();

		logger.debug("보관함 검색 시작");
		bookmarkList = bookmarkService.getSearchList(params);
		result.put("bookmark", bookmarkList);
		
		// 카테고리 (전체 / 영화 / 드라마 / 뮤지컬에 따라 달라지기)
		String type = (String) params.get("type");

		logger.debug("검색 타입 확인 : " + type);
		if (StringUtils.equals(type, CommonId.MOVIE.value())) { // 영화
			logger.debug("영화 검색 시작");
			movieList = movieService.getSearchList(params);
			result.put("movie", movieList);
		} else if (StringUtils.equals(type, CommonId.TV.value())) { // 드라마
			logger.debug("드라마 검색 시작");
			tvList = tvService.getSearchList(params);
			result.put("tv", tvList);
		} else if (StringUtils.equals(type, CommonId.MUSICAL.value())) { // 뮤지컬
			logger.debug("뮤지컬 검색 시작");
			musicalList = kopisService.getSearchList(params);
			result.put("musical", musicalList);
		} else { // 전체
			logger.debug("전체 검색 시작 ");
			movieList = movieService.getSearchList(params);
			result.put("movie", movieList);

			tvList = tvService.getSearchList(params);
			result.put("tv", tvList);;

			musicalList = kopisService.getSearchList(params);
			result.put("musical", musicalList);
		}
		logger.debug("검색 종료 return");
		result.put("type", type);
		return result;
	}

}
