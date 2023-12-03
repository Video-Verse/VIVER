package com.project.viver.service.api;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.viver.common.component.HttpClientComponent;
import com.project.viver.common.constraint.DefaultValue;
import com.project.viver.common.util.DateUtil;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.BusinessException;

@Service
public class MusicalApiService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${api.kopis}")
	private String apiKey;

	@Value("${baseUrl.kopis}")
	private String baseUrl;

	@Autowired
	HttpClientComponent httpClientComponent;

	/**
	 * 모든 뮤지컬 목록 api (배치용)
	 * 
	 * @param params
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> getAllListApi(Map<String, Object> params, int cpage) throws ParseException {
		Map<String, Object> dbs = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> header = new HashMap<String, Object>();

		logger.debug("musical send List api start");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("service", apiKey); // 인증키
		param.put("stdate", DateUtil.AddDate(DateUtil.getSimpleDate(), -3650)); // 공연시작일 (10년전)
		param.put("eddate", DateUtil.AddDate(DateUtil.getSimpleDate(), 365)); // 공연종료일 (1년뒤)
		param.put("cpage", String.valueOf(cpage)); // 현재페이지
		param.put("rows", String.valueOf(DefaultValue.DEFAULT_ROWS)); // 페이지당 목록수
		// param.put("shprfnm", params.get("keyword")); // 공연명 키워드

		try {
			logger.debug("musical http connect");
			result = httpClientComponent.get(baseUrl, "/pblprfr", header, param);
			if(result == null) {
				 
			} else {
				Object dbsValue = result.get("dbs");
				if (dbsValue instanceof Map) {
					dbs = (Map<String, Object>) dbsValue;
				} else {
					return null;
				}
			}

		} catch (BusinessException e) {
			logger.debug("musical send api error");
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}

		return dbs;
	}

	/**
	 * 뮤지컬 상세 api 연결
	 * 
	 * @param params
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> sendDetailApi(String mt20id) throws ParseException {
		Map<String, Object> dbs = new HashMap<String, Object>();
		Map<String, Object> dbMap = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();

		logger.debug("musical send Detail api start");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("service", apiKey); // 인증키
		param.put("mt20id", mt20id); // mt20id

		try {
			logger.debug("musical http connect");
			result = httpClientComponent.get(baseUrl, "/pblprfr/" + mt20id, new HashMap<String, Object>(), param);
			Object dbsValue = result.get("dbs");
			if (dbsValue instanceof Map) {
				dbs = (Map<String, Object>) dbsValue;
			} else {
				return null;
			}

			dbMap = (Map<String, Object>) dbs.get("db");
		} catch (BusinessException e) {
			logger.debug("musical send api error");
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}

		return dbMap;
	}
}
