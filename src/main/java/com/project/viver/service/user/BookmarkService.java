package com.project.viver.service.user;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.viver.entity.user.Bookmark;
import com.project.viver.repository.common.CommonRepository;
import com.project.viver.repository.user.BookmarkRepository;
import com.project.viver.service.common.BaseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookmarkService extends BaseService<Bookmark, String, BookmarkRepository> {

	public BookmarkService(BookmarkRepository repository) {
		super(repository);
	}

	@Autowired
	CommonRepository commonRepository;

	@Autowired
	BookmarkRepository bookmarkRepository;
	
	@Autowired
	BookmarkSpecification bookmarkSpecification;

	protected Logger logger = LoggerFactory.getLogger(getClass());

//    /**
//	 * get List
//	 *
//	 * @param params
//	 * @return
//	 */
//	public List<Map<String, Object>> getList(Map<String, Object> params) {
//		logger.debug("movie db get List start");
//		List<Map<String, Object>> list = movieRepository.getList((String) params.get("keyword"));
//		logger.debug("movie db get List end");
//		return list;
//	}
	
	/**
	 * 검색 리스트 
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getSearchList(Map<String, Object> params) {
		logger.debug("movie db get List start");
		List<Map<String, Object>> list = bookmarkRepository.getSearchList((String) params.get("keyword"));
		logger.debug("movie db get List end");
		return list;
	}
	
	/**
	 * 검색 리스트 
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getSearchList2(Map<String, Object> params) {
		List<Map<String, Object>> list = bookmarkSpecification.searchBookmarksByType(params);	
		return list;
	}

}