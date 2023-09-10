package com.project.viver.service.api;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.viver.common.component.HttpClientComponent;
import com.project.viver.common.constraint.CommonId;
import com.project.viver.common.response.service.ResponseService;
import com.project.viver.common.util.DateUtil;
import com.project.viver.entity.kopis.Musical;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.BusinessException;
import com.project.viver.repository.api.KopisRepository;
import com.project.viver.repository.common.CommonRepository;

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
	
	@Autowired
	CommonRepository commonRepository;

	/**
	 * 뮤지컬 api 연결
	 * 
	 * @param params
	 * @return
	 * @throws ParseException
	 */
	public List<Map<String, Object>> sendApi(Map<String,Object> params) throws ParseException {
		List<Map<String, Object>> list = new ArrayList();
		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> header = new HashMap<String, Object>();
		
		int cpage = 1;
		int rows = 10;
		int loop = 5;

		for(int i = 0; i< loop; i++) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("service", apiKey);			             //인증키
			param.put("stdate", "20100101");			             //공연시작일
			param.put("eddate", DateUtil.AddDate(DateUtil.getSimpleDate(), 365));			//공연종료일 (1년뒤)
			param.put("cpage", String.valueOf(cpage));				              //현재페이지
			param.put("rows", String.valueOf(rows));				              //페이지당 목록수
			param.put("shprfnm", params.get("keyword"));			              //공연명 키워드
			
			try {
				result = httpClientComponent.get(baseUrl, "/pblprfr", header, param);
				Map<String, Object> dbs = (Map<String, Object>) result.get("dbs");
				List<Map<String, Object>> db = (List<Map<String, Object>>) dbs.get("db");
				
				
				for (Map<String,Object> map : db ) {
					Musical musical = new Musical();
					musical.setMv_id(commonRepository.getId(CommonId.MUSICAL.value()));
					musical.setMt20id((String)map.get("mt20id"));
					musical.setPrfnm((String)map.get("prfnm"));
					musical.setGenrenm((String)map.get("genrenm"));
					musical.setPrfstate((String)map.get("prfstate"));
					musical.setPrfpdfrom(StringUtils.remove((String)map.get("prfpdfrom"),"."));
					musical.setPrfpdto(StringUtils.remove((String)map.get("prfpdto"),"."));
					musical.setPoster((String)map.get("poster"));
					musical.setFcitynm((String)map.get("fcltynm"));
					musical.setOpenrun((String)map.get("openrun"));
					musical.setDelYn("N");
					kopisRepository.save(musical);
				}
			} catch (BusinessException e) {
				throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
			}
			cpage++;
		}
		return list;
	}
	
	
	/**
	 * db에서 가져오기
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> getList(Map<String,Object> params) {
		List<Map<String,Object>> list = kopisRepository.getList((String)params.get("keyword"));
		//List<Musical> list = kopisRepository.getList((String)params.get("keyword"));
		
		return list;
	}
	
	//단건
	
	//insert
	
	//delete

}