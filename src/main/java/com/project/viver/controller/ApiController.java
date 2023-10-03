package com.project.viver.controller;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.viver.common.response.model.ListResult;
import com.project.viver.common.response.service.ResponseService;
import com.project.viver.service.ApiService;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ResponseService responseService;
	
	@Autowired
	ApiService apiService;
	
	/**
	 * 검색
	 */
	@PostMapping(value = "/search")
	public ListResult<Map<String, Object>> search(@RequestBody Map<String, Object> params) throws ParseException {
		return responseService.getListResult(apiService.search(params));
	}
}
