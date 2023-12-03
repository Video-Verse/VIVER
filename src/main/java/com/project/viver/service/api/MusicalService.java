package com.project.viver.service.api;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.viver.common.constraint.CommonId;
import com.project.viver.common.constraint.DefaultValue;
import com.project.viver.common.response.service.ResponseService;
import com.project.viver.entity.kopis.Musical;
import com.project.viver.repository.api.MusicalRepository;
import com.project.viver.repository.common.CommonRepository;
import com.project.viver.service.common.BaseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MusicalService extends BaseService<Musical, String, MusicalRepository> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public MusicalService(MusicalRepository repository) {
		super(repository);
	}

	@Autowired
	MusicalApiService musicalApiService;

	@Autowired
	ResponseService responseService;

	@Autowired
	MusicalRepository musicalRepository;

	@Autowired
	CommonRepository commonRepository;

	/**
	 * getList
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getList(Map<String, Object> params) {
		logger.debug("musical db get List start");
		List<Map<String, Object>> list = musicalRepository.getList((String) params.get("keyword"));
		// List<Musical> list = kopisRepository.getList((String)params.get("keyword"));
		logger.debug("musical db get List end");
		return list;
	}

	/**
	 * 검색 리스트 (id, 포스터, 이름)
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getSearchList(Map<String, Object> params) {
		logger.debug("musical db get search List start");
		List<Map<String, Object>> list = musicalRepository.getSearchList((String) params.get("keyword"));
		logger.debug("musical db get search List end");
		return list;
	}

	public Musical get(String mv_id) {
		logger.debug("musical db get select");
		return super.get(mv_id);
	}

	/**
	 * 배치용 후처리
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> batch(Map<String, Object> params) {
		// 맨첨 배치 돌린다 -> 일단 insert 함 -> 상세 없는 애들 id만 뽑음 -> api 반복 돌림

		// 이후 배치 -> 기존에 id들을 다 가져옴 -> api 리스트에서 비교 -> id 일치 하지 않는 애들만 고름
		// -> 걔네들 insert -> 그 id 기준으로 상세 api 돌리기 -> update
		// 또는 그 로직안에서 상세 api 돌려서 set 하고 -> insert

		// 만약 배치 제대로 돌렸는데도 상세가 없다 -> 매일 밤에 그것들만 따로 모아서 상세api 돌아야할듯

		// 1. db에서 musical id만 가져오기
		List<Musical> musicalList = musicalRepository.findAll();
		List<String> musicalIdList = musicalList.stream().map(Musical::getMt20id).collect(Collectors.toList());
		// 2. 배치돌려서 가져온 리스트에서 id만 추출

		// 3. 2개의 id 리스트를 비교하여

		logger.debug("musical db get search List start");
		List<Map<String, Object>> list = musicalRepository.getSearchList((String) params.get("keyword"));
		logger.debug("musical db get search List end");
		return list;
	}

	/**
	 * 뮤지컬 목록 api 연결 (배치용)
	 * 
	 * @param params
	 * @return
	 * @throws ParseException
	 */
	public List<Map<String, Object>> sendListApi(Map<String, Object> params) throws ParseException {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> dbs = new HashMap<String, Object>();
		Map<String, Object> defaultResult = new HashMap<String, Object>();

		logger.debug("musical send List api start");

		for (int i = 1; i <= DefaultValue.DEFAULT_LOOP_SIZE; i++) {
			Musical musical = new Musical();

			defaultResult = musicalApiService.getAllListApi(params, i);

			if (defaultResult != null) {
				Object db = dbs.get("db");
				if (db instanceof Map) { // 응답데이터가 1개임
					Map<String, Object> dbMap = (Map<String, Object>) db;
					postProcess(dbMap, musical);

				} else if (db instanceof List) { // 응답데이터 list
					List<Map<String, Object>> dbList = (List<Map<String, Object>>) db;

					logger.debug("musical http connect end");
					for (Map<String, Object> map : dbList) {
						postProcess(map, musical);
						//Thread.sleep(300);
					}
				}
			} else {
				// db 끝 =>
				return null;
			}
		}

		return list;
	}

	public void postProcess(Map<String, Object> db, Musical musical) throws ParseException {
		Map<String, Object> detailResult = new HashMap<String, Object>();
		logger.debug("뮤지컬 공연 목록 api 응답 데이터 기본 세팅");
		musical = defaultSet(db);
		logger.debug("해당 Musical mt20id로 상세 조회 및 세팅");
		detailResult = musicalApiService.sendDetailApi(musical.getMt20id());

		musical = detailSet(detailResult, musical);
		logger.debug("Musical insert");
		super.insert(musical);
	}

	/**
	 * 공연 목록 api 응답 기본 세팅값
	 * 
	 * @param map
	 * @return
	 */
	public Musical defaultSet(Map<String, Object> map) {
		Musical musical = new Musical();
		musical.setMv_id(commonRepository.getId(CommonId.MUSICAL.value()));
		musical.setMt20id((String) map.get("mt20id"));
		musical.setPrfnm((String) map.get("prfnm"));
		musical.setGenrenm((String) map.get("genrenm"));
		musical.setPrfstate((String) map.get("prfstate"));
		musical.setPrfpdfrom(StringUtils.remove((String) map.get("prfpdfrom"), "."));
		musical.setPrfpdto(StringUtils.remove((String) map.get("prfpdto"), "."));
		musical.setPoster((String) map.get("poster"));
		musical.setFcitynm((String) map.get("fcltynm"));
		musical.setOpenrun((String) map.get("openrun"));

		return musical;
	}

	/**
	 * 공연 상세 api 응답 세팅값
	 * 
	 * @param map
	 * @param musical
	 * @return
	 */
	public Musical detailSet(Map<String, Object> map, Musical musical) {
		musical.setPrfcast((String) map.get("prfcast"));
		musical.setPrfcrew((String) map.get("prfcrew"));
		musical.setPrfruntime((String) map.get("prfruntime"));
		musical.setPrfage((String) map.get("prfage"));
		musical.setEntrpsnm((String) map.get("entrpsnm"));
		musical.setSty((String) map.get("sty"));
		musical.setPcseguidance((String) map.get("pcseguidance"));
		musical.setDtguidance((String) map.get("dtguidance"));
		musical.setDelYn("N");

		return musical;
	}

//	public void insert(Map<String, Object> map) {
//		logger.debug("musical db get select");
//		Musical musical = new Musical();
//		musical.setMv_id(commonRepository.getId(CommonId.MUSICAL.value()));
//		musical.setMt20id((String) map.get("mt20id"));
//		musical.setPrfnm((String) map.get("prfnm"));
//		musical.setGenrenm((String) map.get("genrenm"));
//		musical.setPrfstate((String) map.get("prfstate"));
//		musical.setPrfpdfrom(StringUtils.remove((String) map.get("prfpdfrom"), "."));
//		musical.setPrfpdto(StringUtils.remove((String) map.get("prfpdto"), "."));
//		musical.setPoster((String) map.get("poster"));
//		musical.setFcitynm((String) map.get("fcltynm"));
//		musical.setOpenrun((String) map.get("openrun"));
//		super.insert(musical);
//	}

//	/**
//	 * 공연 상세 조회해서 넣는 것
//	 * 
//	 * @param param
//	 */
//	public void update(Map<String, Object> param) {
//		Musical musical = new Musical();
//		musical.setPrfcast((String) param.get("prfcast"));
//		musical.setPrfcrew((String) param.get("prfcrew"));
//		musical.setPrfruntime((String) param.get("prfruntime"));
//		musical.setPrfage((String) param.get("prfage"));
//		musical.setEntrpsnm((String) param.get("entrpsnm"));
//		musical.setSty((String) param.get("sty"));
//		musical.setPcseguidance((String) param.get("pcseguidance"));
//		musical.setDtguidance((String) param.get("dtguidance"));
//		musical.setDelYn("N");
//
//		super.update((String) param.get("mv_id"), musical);
//	}

	// delete

}