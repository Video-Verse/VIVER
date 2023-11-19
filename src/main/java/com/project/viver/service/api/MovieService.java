package com.project.viver.service.api;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.viver.common.component.HttpClientComponent;
import com.project.viver.common.constraint.CommonId;
import com.project.viver.entity.tmdb.Movie;
import com.project.viver.error.ErrorCode;
import com.project.viver.error.exception.BusinessException;
import com.project.viver.repository.api.MovieRepository;
import com.project.viver.repository.common.CommonRepository;
import com.project.viver.service.common.BaseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieService extends BaseService<Movie, String, MovieRepository> {

	public MovieService(MovieRepository repository) {
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
	CommonRepository commonRepository;

	@Autowired
	MovieRepository movieRepository;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	 //리스트 조회 및 등록
    public Map<String, Object> getMovieDiscover() {
    	Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> header = new HashMap<String, Object>();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("api_key", apiKey);
		param.put("language", language);

		try {
			int page = 1;
			int totalPage = 0;
			int totalCnt = 0;
			do {
				param.put("page", page);
				result = httpClientComponent.get(baseUrl, "/discover/movie", header, param);
				totalPage = (int) result.get("total_pages");
				List<Map<String, Object>> results = (List<Map<String, Object>>) result.get("results");
				if (results.size() > 1) {
					for (Map<String, Object> map : results) {
						// logger.info(map.get("id").toString());
						// logger.info(map.get("title").toString());
						// logger.info(map.get("adult").toString());
						String movieId = commonRepository.getId(CommonId.MOVIE.value());
						Movie movie = new Movie(movieId
								, Long.parseLong(map.get("id").toString())
								, map.get("title").toString()
								, "false".equals(map.get("adult").toString()) ? "N" : "Y"
								, map.get("original_language").toString()
								, map.get("original_title").toString()
								, map.get("overview") != null ? map.get("overview").toString() : null
								, map.get("poster_path") != null ? map.get("poster_path").toString() : null
								, map.get("backdrop_path") != null ? map.get("backdrop_path").toString() : null
								, map.get("vote_average") != null ? Float.parseFloat(map.get("vote_average").toString()) : null
								, map.get("vote_count") != null ? Long.parseLong(map.get("vote_count").toString()) : null
								, map.get("release_date").toString().replaceAll("-", "")
								, map.get("popularity") != null ? Float.parseFloat(map.get("popularity").toString()) : null);
						super.insert(movie);
						totalCnt++;
					}
				}
				logger.info("page 갯수 " +page);
			} while (totalPage >= ++page  && page < 15);
			logger.info(totalCnt + "개 저장 끝!!!!!!!!");
		} catch (BusinessException e) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return result;
    }

    /**
	 * get List
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
	
	/**
	 * 검색 리스트 
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getSearchList(Map<String, Object> params) {
		logger.debug("movie db get List start");
		List<Map<String, Object>> list = movieRepository.getSearchList((String) params.get("keyword"));
		logger.debug("movie db get List end");
		return list;
	}

}