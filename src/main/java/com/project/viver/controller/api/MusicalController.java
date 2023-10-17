package com.project.viver.controller.api;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.viver.common.response.model.ListResult;
import com.project.viver.common.response.service.ResponseService;
import com.project.viver.service.api.MusicalService;

@RestController
@RequestMapping("/api/kopis")
public class MusicalController {

	@Autowired
	private ResponseService responseService;

	@Autowired
	private MusicalService kopisService;

	/**
	 * 뮤지컬 목록 api 
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@PostMapping(value = "/search")
	public ListResult<Map<String, Object>> sendListApi(@RequestBody Map<String,Object> params) throws ParseException {
		return responseService.getListResult(kopisService.sendListApi(params));
	}
	
//	/**
//	 * 뮤지컬 목록 api 
//	 * 
//	 * @return
//	 * @throws ParseException 
//	 */
//	@PostMapping(value = "/search")
//	public ListResult<Map<String, Object>> sendDetailApi(@RequestBody Map<String,Object> params) throws ParseException {
//		return responseService.getListResult(kopisService.sendDetailApi(params));
//	}
}
