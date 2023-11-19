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
import com.project.viver.service.api.DramaService;
import com.project.viver.service.api.MovieService;
import com.project.viver.service.api.MusicalService;

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
	DramaService dramaService;

	/**
	 * 검색
	 * 
	 * @param params
	 * @return
	 * @throws ParseException
	 */
	public List<Map<String, Object>> search(Map<String, Object> params) throws ParseException {
		logger.debug("검색 시작");
		List<Map<String, Object>> result = new ArrayList<>();

		List<Map<String, Object>> movieList = new ArrayList<>();
		List<Map<String, Object>> dramaList = new ArrayList<>();
		List<Map<String, Object>> musicalList = new ArrayList<>();

		Map<String, Object> musical = new HashMap<>();
		Map<String, Object> movie = new HashMap<>();
		Map<String, Object> drama = new HashMap<>();

		// 카테고리 (전체 / 영화 / 드라마 / 뮤지컬에 따라 달라지기)
		String type = (String) params.get("type");

		logger.debug("검색 타입 확인 : " + type);
		if (StringUtils.equals(type, CommonId.MOVIE.value())) { // 영화
			logger.debug("영화 검색 시작");
			movieList = movieService.getSearchList(params);
			movie.put("movie", movieList);
			result.add(movie);
		} else if (StringUtils.equals(type, CommonId.DRAMA.value())) { // 드라마
			logger.debug("드라마 검색 시작");
			dramaList = dramaService.getSearchList(params);
			drama.put("drama", dramaList);
			result.add(drama);
		} else if (StringUtils.equals(type, CommonId.MUSICAL.value())) { // 뮤지컬
			logger.debug("뮤지컬 검색 시작");
			musicalList = kopisService.getSearchList(params);
			musical.put("musical", musicalList);
			result.add(musical);
		} else { // 전체
			logger.debug("전체 검색 시작 ");
			movieList = movieService.getSearchList(params);
			movie.put("movie", movieList);
			result.add(movie);

			dramaList = dramaService.getSearchList(params);
			drama.put("drama", dramaList);
			result.add(drama);

			musicalList = kopisService.getSearchList(params);
			musical.put("musical", musicalList);
			result.add(musical);
		}
		logger.debug("검색 종료 return");

		return result;
	}

}
