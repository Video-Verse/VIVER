package com.project.viver.service.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.viver.common.component.HttpClientComponent;
import com.project.viver.common.constraint.CommonId;
import com.project.viver.entity.tmdb.Drama;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.BusinessException;
import com.project.viver.repository.api.DramaRepository;
import com.project.viver.repository.common.CommonRepository;
import com.project.viver.service.common.BaseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DramaService extends BaseService<Drama, String, DramaRepository>{

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public DramaService(DramaRepository repository) {
		super(repository);
	}

	@Value("${baseUrl.tmdb}")
	private String baseUrl;

	@Value("${api.language}")
    private String language;

	@Value("${api.tmdb}")
	private String apiKey;

	@Autowired
	HttpClientComponent httpClientComponent;

	@Autowired
	DramaRepository dramaRepository;

	@Autowired
	CommonRepository commonRepository;

	 //리스트 조회 및 등록
    public List<Map<String, Object>> getDramaDiscover() {
    	Map<String, Object> result = new HashMap<String, Object>();
    	List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

		Map<String, Object> header = new HashMap<String, Object>();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("api_key", apiKey);
		param.put("language", language);

		try {
			result = httpClientComponent.get(baseUrl, "/discover/tv", header, param);
			results = (List<Map<String, Object>>) result.get("results");

			 if (results.size() > 1) {
			 	for (Map<String, Object> map : results) {
			 		 logger.info(map.get("id").toString());
			 		 logger.info(map.get("name").toString());
			 		String DramaId = commonRepository.getId(CommonId.DRAMA.value());
			 		Drama drama = new Drama(DramaId
			 				, Long.parseLong(map.get("id").toString())
			 				, map.get("name").toString()
			 				, map.get("original_language").toString(), map.get("original_name").toString()
			 				, map.get("overview").toString()
			 				, map.get("poster_path").toString()
			 				, map.get("backdrop_path").toString()
			 				, Float.parseFloat(map.get("vote_average").toString())
			 				, Long.parseLong(map.get("vote_count").toString())
			 				, map.get("first_air_date").toString().replaceAll("-", "")
			 				, Float.parseFloat(map.get("popularity").toString()));
			 		super.insert(drama);
			 	}
			 }


		} catch (BusinessException e) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return results;
    }


	/**
	 * db에서 가져오기
	 *
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getList(Map<String, Object> params) {
		logger.debug("drama db get List start");
		List<Map<String, Object>> list = dramaRepository.getList((String) params.get("keyword"));
		logger.debug("drama db get List end");
		return list;
	}

}
