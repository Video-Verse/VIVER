package com.project.viver.service.api;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.viver.common.component.HttpClientComponent;
import com.project.viver.common.response.service.ResponseService;
import com.project.viver.common.util.DateUtil;
import com.project.viver.entity.kopis.Musical;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.BusinessException;
import com.project.viver.repository.api.KopisRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KopisService {

	@Value("${api.kopis}")
	private String apiKey;

	@Value("${baseUrl.kopis}")
	private String baseUrl;

	@Autowired
	HttpClientComponent httpClientComponent;

	@Autowired
	ResponseService responseService;
	
	@Autowired
	KopisRepository kopisRepository;

	// 리스트 조회
	public List<Map<String, Object>> searchKopis() throws ParseException {
		List<Map<String, Object>> list = new ArrayList();
		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> header = new HashMap<String, Object>();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("service", apiKey);			             //인증키
		param.put("stdate", "20160101");			             //공연시작일
		param.put("eddate", DateUtil.AddDate(DateUtil.getSimpleDate(), 365));			//공연종료일 (1년뒤)
		param.put("cpage", "1");				              //현재페이지
		param.put("rows", "10");				              //페이지당 목록수
		param.put("shprfnm", "사랑");			              //공연명 키워드

		try {
			result = httpClientComponent.get(baseUrl, "/pblprfr", header, param);
			Map<String, Object> dbs = (Map<String, Object>) result.get("dbs");
			List<Map<String, Object>> db = (List<Map<String, Object>>) dbs.get("db");
			
			
			for (Map<String,Object> map : db ) {
				Musical musical = new Musical();
				musical.setMv_id(apiKey);
				musical.setMt20id((String)map.get("mt20id"));
				musical.setPrfnm((String)map.get("prfnm"));
				musical.setGenrenm((String)map.get("genrenm"));
				musical.setPrfstate((String)map.get("prfstate"));
				musical.setPrfpdfrom((String)map.get("prfpdfrom"));
				musical.setPrfpdto((String)map.get("prfpdto"));
				musical.setPoster((String)map.get("poster"));
				musical.setFcitynm((String)map.get("fcltynm"));
				musical.setOpenrun((String)map.get("openrun"));
				kopisRepository.save(musical);
				
			}
			if (result == null) {
				result = new HashMap<String, Object>();
				return list;
			}
		} catch (BusinessException e) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return list;
	}
	
	
	
	//list
	
	//단건
	
	//insert
	
	//delete

}