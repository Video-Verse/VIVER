package com.project.viver.service.api;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.viver.common.component.HttpClientComponent;
import com.project.viver.common.constraint.CommonId;
import com.project.viver.entity.tmdb.Tv;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.BusinessException;
import com.project.viver.repository.api.TvRepository;
import com.project.viver.repository.common.CommonRepository;
import com.project.viver.service.common.BaseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TvService extends BaseService<Tv, String, TvRepository> {

	public TvService(TvRepository repository) {
		super(repository);
	}

    private String baseUrl = "https://api.themoviedb.org/3";

    private String language = "ko-KR";

	@Value("${api.tmdb}")
	private String apiKey;

	@Autowired
	HttpClientComponent httpClientComponent;

	@Autowired
	CommonRepository commonRepository;

	// protected Logger logger = LoggerFactory.getLogger(getClass());

	 //리스트 조회 및 등록
    public List<Map<String, Object>> getTvDiscover() {
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
			 		// logger.info(map.get("id").toString());
			 		// logger.info(map.get("name").toString());
			 		String TvId = commonRepository.getId(CommonId.TV.value());
			 		Tv tv = new Tv(TvId
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
			 		super.insert(tv);
			 	}
			 }


		} catch (BusinessException e) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return results;
    }


}