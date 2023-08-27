package com.project.viver.controller.api;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.viver.common.response.model.ListResult;
import com.project.viver.common.response.service.ResponseService;
import com.project.viver.service.api.KopisService;

@RestController
@RequestMapping("/api/kopis")
public class KopisController {

	@Autowired
	private ResponseService responseService;

	@Autowired
	private KopisService kopisService;

	// insert
	/**
	 * @return
	 * @throws ParseException 
	 */
	@PostMapping(value = "/search")
	public ListResult<Map<String, Object>> searchKopis() throws ParseException {
		return responseService.getListResult(kopisService.searchKopis());
	}
}
