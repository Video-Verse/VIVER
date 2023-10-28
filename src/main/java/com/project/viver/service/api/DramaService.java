package com.project.viver.service.api;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.viver.entity.tmdb.DramaSample;
import com.project.viver.repository.api.DramaRepository;
import com.project.viver.repository.common.CommonRepository;
import com.project.viver.service.common.BaseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DramaService extends BaseService<DramaSample, String, DramaRepository>{

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public DramaService(DramaRepository repository) {
		super(repository);
	}
	
	@Autowired
	DramaRepository dramaRepository;

	@Autowired
	CommonRepository commonRepository;
	
	
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
