package com.project.viver.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.viver.repository.common.CommonRepository;

@Service
public class CommonService {

	@Autowired
	CommonRepository commonRepository; 
	
	public String getId(String word) {
		return commonRepository.getId(word);
	}
}
