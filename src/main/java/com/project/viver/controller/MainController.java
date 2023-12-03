package com.project.viver.controller;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.viver.common.response.model.SingleResult;
import com.project.viver.common.response.service.ResponseService;
import com.project.viver.service.ApiService;

@RestController
public class MainController {

	@Autowired
	private ResponseService responseService;
	
	@Autowired
	ApiService apiService;
	
	/**
	 * 검색
	 */
	@PostMapping(value = "/search")
	public SingleResult<Map<String, Object>> search(@RequestBody Map<String, Object> params) throws ParseException {
		return responseService.getSingleResult(apiService.search(params));
	}
}
