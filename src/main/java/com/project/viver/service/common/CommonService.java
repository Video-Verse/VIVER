package com.project.viver.service.common;

import org.springframework.stereotype.Service;

import com.project.viver.repository.common.CommonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonService {

	private final CommonRepository commonRepository; 
	
	public String getId(String word) {
		return commonRepository.getId(word);
	}
}
