package com.project.viver.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.project.viver.entity.RegContents;

public interface RegContentsRepositoryCustom {

	Page<RegContents> search(Map<String, String> params, PageRequest pageable);
	
}
