package com.project.viver.service.api;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.viver.common.component.HttpClientComponent;
import com.project.viver.common.constraint.CommonId;
import com.project.viver.entity.tmdb.Movie;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.BusinessException;
import com.project.viver.repository.api.MovieRepository;
import com.project.viver.repository.common.CommonRepository;
import com.project.viver.service.common.BaseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieService extends BaseService<Movie, String, MovieRepository> {

	public MovieService(MovieRepository repository) {
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
	
	@Autowired
	MovieRepository movieRepository;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	 //리스트 조회 및 등록
    public Map<String, Object> getDiscover() {
    	Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> header = new HashMap<String, Object>();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("api_key", apiKey);
		param.put("language", language);

		try {
			result = httpClientComponent.get(baseUrl, "/discover/movie", header, param);
			List<Map<String, Object>> results = (List<Map<String, Object>>) result.get("results");

			if (results.size() > 1) {
				for (Map<String, Object> map : results) {
					logger.info(map.get("id").toString());
					logger.info(map.get("title").toString());
					logger.info(map.get("adult").toString());
					String movieId = commonRepository.getId(CommonId.MOVIE.value());
					Movie movie = new Movie(movieId
							, Long.parseLong(map.get("id").toString())
							, map.get("title").toString()
							, "false".equals(map.get("adult").toString()) ? "N" : "Y"
							, map.get("original_language").toString(), map.get("original_title").toString()
							, map.get("overview").toString()
							, map.get("poster_path").toString()
							, map.get("backdrop_path").toString()
							, Float.parseFloat(map.get("vote_average").toString())
							, Long.parseLong(map.get("vote_count").toString())
							, map.get("release_date").toString().replaceAll("-", "")
							, Float.parseFloat(map.get("popularity").toString()));
					super.insert(movie);
				}
			}


		} catch (BusinessException e) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return result;
    }
    
    /**
	 * db에서 가져오기
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getList(Map<String, Object> params) {
		logger.debug("movie db get List start");
		List<Map<String, Object>> list = movieRepository.getList((String) params.get("keyword"));
		logger.debug("movie db get List end");
		return list;
	}

}